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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.model.Artista;
import it.uniroma3.model.Utente;
import it.uniroma3.service.ArtistaService;
import it.uniroma3.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private UtenteService utenteService;

    
    @Value("${app.upload.dir}")
    private String uploadDir;  

    @GetMapping("/artista/{id}")
    public String getArtista(@PathVariable Long id, Model model) {
        Optional<Artista> artistaOpt = artistaService.getArtistaById(id);
        if (artistaOpt.isPresent()) {
            model.addAttribute("artista", artistaOpt.get());
            return "Artista";
        } else
            return "redirect:/artisti"; // artista non trovato
    }

    @GetMapping("/artisti")
    public String getArtisti(Model model) {
        model.addAttribute("artisti", artistaService.getAllArtisti());
        return "artisti";
    }

    // Mostra form per aggiungere nuovo artista
    @GetMapping("/artista/aggiungi")
    public String mostraFormArtista(Model model) {
        model.addAttribute("artista", new Artista());
        return "formArtista";
    }

    @GetMapping("/artista/modifica/{id}")
    public String mostraFormModificaArtista(@PathVariable Long id, Model model) {
        Artista artista = artistaService.getArtistaById(id)
                .orElseThrow(() -> new RuntimeException("Artista non trovato"));
        model.addAttribute("artista", artista);
        return "formArtista";
    }

    @PostMapping("/artista/salva")
    public String salvaArtista(@Valid @ModelAttribute("artista") Artista artista,
            BindingResult bindingResult,
            @RequestParam("fileImage") MultipartFile fileImage,
            Model model) {



        if (bindingResult.hasErrors()) {
            return "formArtista";
        }

        if (fileImage != null && !fileImage.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + fileImage.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);

            try {
                Files.createDirectories(uploadPath); // create dirs if not exists
                Path filePath = uploadPath.resolve(filename);
                fileImage.transferTo(filePath.toFile());

                artista.setUrlImage("images/" + filename); // relativo alla cartella static

            } catch (IOException e) {
                model.addAttribute("errore", "Errore nel caricamento immagine: " + e.getMessage());
                model.addAttribute("artista", artista);
                return "formArtista";
            }
        }

        artistaService.aggiungiArtista(artista);
        return "redirect:/artista/" + artista.getId();
    }

    // Elimina artista
    @PostMapping("/artista/elimina/{id}")
    public String eliminaArtista(@PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {

        Utente utente = utenteService.getUtenteByEmail(currentUser.getUsername())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        artistaService.cancellaArtista(id, utente);

        return "redirect:/artisti";
    }

}
