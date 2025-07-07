package it.uniroma3.controller;

//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Artista;
//import it.uniroma3.model.Museo;
//import it.uniroma3.model.Artista;
//import it.uniroma3.model.Museo;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.service.ArtistaService;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.MuseoService;
import it.uniroma3.service.OperaService;
import it.uniroma3.service.UtenteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class OperaController {

    @Autowired
    private OperaService operaService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private MuseoService museoService;

    @Autowired
    private UtenteService utenteService; 

    @Value("${app.upload.dir}")
    private String uploadDir;  

    @GetMapping("/opera/{id}")
    public String getOpera(@PathVariable Long id, Model model) {
        Optional<Opera> operaOpt = operaService.getOperaById(id);
        if (operaOpt.isPresent()) {
            model.addAttribute("opera", operaOpt.get());
            return "Opera";
        } else {
            // Puoi gestire l’errore come preferisci: redirect, pagina di errore, messaggio,
            // ecc.
            return "redirect:/Opere"; // ad esempio torna alla lista opere
        }
    }

    @GetMapping("/opere")
    public String getOpere(Model model) {
        model.addAttribute("opere", this.operaService.getAllOpere());
        return "Opere";
    }

    @GetMapping("/opera/aggiungi")
    public String formAggiungiOpera(Model model) {
        Opera opera = new Opera();          // <— nuova istanza
        opera.setArtista(new Artista());

        model.addAttribute("opera", opera);
        model.addAttribute("museoGestito", museoService.getMuseoUnico());
        model.addAttribute("artisti", artistaService.getAllArtisti());

        return "formOpera";
    }

    @GetMapping("/opera/modifica/{id}")
    public String formModificaOpera(@PathVariable Long id, Model model) {
        Optional<Opera> operaOpt = operaService.getOperaById(id);
        if (operaOpt.isPresent()) {
            model.addAttribute("opera", operaOpt.get());
            // Aggiungi anche artisti e museo se li usi nel form
            model.addAttribute("artisti", artistaService.getAllArtisti());
            model.addAttribute("museoGestito", museoService.getMuseoUnico());
            return "formOpera";
        } else {
            return "redirect:/opere";
        }
    }

    @PostMapping("/opera/salva")
    public String salvaOpera(
            @Valid @ModelAttribute("opera") Opera opera,
            BindingResult bindingResult,
            @RequestParam("artista.id") Long artistaId,
            @RequestParam("fileImage") MultipartFile fileImage,
            Model model) {

        // Se ci sono errori di validazione, torniamo al form
        if (bindingResult.hasErrors()) {
            // Evita il null pointer in Thymeleaf
            if (opera.getArtista() == null) {
                opera.setArtista(new Artista());
            }
            model.addAttribute("artisti", artistaService.getAllArtisti());
            model.addAttribute("museoGestito", museoService.getMuseoUnico());
            return "formOpera";
        }
        // Associazione artista
        artistaService.getArtistaById(artistaId)
                    .ifPresent(opera::setArtista);

        // Caricamento fileImage
        if (fileImage != null && !fileImage.isEmpty()) {
            String filename = UUID.randomUUID() + "_" +
                            StringUtils.cleanPath(fileImage.getOriginalFilename());
            Path uploadPath = Paths.get(uploadDir);

            try {
                // Creo la cartella (e tutte le sottocartelle) se non esiste
                Files.createDirectories(uploadPath);

                // Salvo il file fisico
                Path filePath = uploadPath.resolve(filename);
                fileImage.transferTo(filePath.toFile());

                // Imposto in Opera solo il nome del file per il DB
                opera.setUrlImage("images/" + filename);
;

            } catch (IOException e) {
                // In caso di errore di I/O torno al form con messaggio
                model.addAttribute("errore", "Impossibile caricare l'fileImage: " + e.getMessage());
                model.addAttribute("artisti", artistaService.getAllArtisti());
                return "formOpera";
            }
        }

        // Salvataggio dell'entità e redirect
        operaService.aggiungiOpera(opera);
        return "redirect:/opere";
    }


    @PostMapping("/opera/elimina/{id}")
    public String eliminaOpera(@PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        Optional<Utente> utente = utenteService.getUtenteByEmail(currentUser.getUsername());
        operaService.cancellaOpera(id, utente);
        return "redirect:/opere";
    }

}
