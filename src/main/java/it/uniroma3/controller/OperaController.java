package it.uniroma3.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Artista;
import it.uniroma3.model.Museo;
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
        model.addAttribute("opera", new Opera());
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
    public String salvaOpera(@Valid @ModelAttribute("opera") Opera opera,
            BindingResult bindingResult,
            @RequestParam("artista.id") Long artistaId,
            @RequestParam("museo.id") Long museoId,
            @RequestParam("urlImage") MultipartFile immagine,
            Model model) {

        Artista artista = artistaService.getArtistaById(artistaId).get();
        Museo museo = museoService.getMuseoUnico();

        opera.setArtista(artista);
        opera.setMuseo(museo);

        // Caricamento immagine
        if (immagine != null && !immagine.isEmpty()) {
            String uploadDir = "C:\\Users\\182935\\Documents\\workspace-spring-tool-suite-4-4.28.1.RELEASE\\progettoPersonaleSIW-1\\uploads\\images\\";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {
                String nomeFile = UUID.randomUUID() + "_" + immagine.getOriginalFilename();
                immagine.transferTo(new File(uploadDir + nomeFile));
                opera.setUrlImage("images/" + nomeFile);
            } catch (IOException e) {
                model.addAttribute("errore", "Errore nel caricamento immagine: " + e.getMessage());
                model.addAttribute("opera", opera);
                model.addAttribute("artisti", artistaService.getAllArtisti());
                model.addAttribute("museoGestito", museo);
                return "formOpera";
            }
        }


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
