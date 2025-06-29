package it.uniroma3.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import it.uniroma3.model.Evento;
import it.uniroma3.model.Fascia;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.OperaService;
import it.uniroma3.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import it.uniroma3.repository.EventoRepository;
import it.uniroma3.repository.FasciaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private OperaService operaService;

    @Autowired
    private FasciaRepository fasciaRepository;

    // visualizza un singolo evento
    @GetMapping("/evento/{id}")
    public String getEvento(@PathVariable Long id, Model model) {
        model.addAttribute("evento", this.eventoService.getEventoById(id));
        return "Evento";
    }

    // visualizza la lista di eventi
    @GetMapping("/eventi")
    public String getEventi(Model model) {
        List<Evento> eventi = eventoRepository.findAll();
        model.addAttribute("eventi", eventi);
        return "Eventi";
    }

    // mostra il form per aggiungere un nuovo evento
    @GetMapping("/evento/aggiungi")
    public String mostraFormEvento(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("tutteLeOpere", operaService.getAllOpere());
        return "formEvento";
    }

    // mostra form di modifica di un evento esistente
    @GetMapping("/evento/modifica/{id}")
    public String mostraFormModificaEvento(@PathVariable Long id, Model model) {
        Evento evento = eventoRepository.findByIdWithOpereAndArtisti(id);
        model.addAttribute("evento", evento);
        model.addAttribute("tutteLeOpere", operaService.getAllOpere()); // se serve

        return "formEvento"; // il nome del tuo HTML
    }

    @PostMapping("/evento/salva")
    public String salvaEvento(
            @ModelAttribute Evento evento,
            @RequestParam(value = "opereIds", required = false) List<Long> opereIds,
            @RequestParam(value = "fasceData", required = false) List<String> fasceData,
            @RequestParam(value = "fasceOrarioInizio", required = false) List<String> fasceOrarioInizio,
            @RequestParam(value = "fasceCapienza", required = false) List<Integer> fasceCapienza,
            @RequestParam(value = "immagine", required = false) MultipartFile immagine,
            HttpServletRequest request,
            Model model) {

        try {
            System.out.println("🔍 Richiesta multipart con parts: " + request.getParts().size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Evento eventoDaSalvare;

        if (evento.getId() != null) {
            eventoDaSalvare = eventoService.getEventoById(evento.getId());
            eventoDaSalvare.setTitolo(evento.getTitolo());
            eventoDaSalvare.setDescrizione(evento.getDescrizione());
            eventoDaSalvare.setDataInizio(evento.getDataInizio());
            eventoDaSalvare.setDataFine(evento.getDataFine());
            eventoDaSalvare.setMuseo(evento.getMuseo());
        } else {
            eventoDaSalvare = new Evento();
            eventoDaSalvare.setTitolo(evento.getTitolo());
            eventoDaSalvare.setDescrizione(evento.getDescrizione());
            eventoDaSalvare.setDataInizio(evento.getDataInizio());
            eventoDaSalvare.setDataFine(evento.getDataFine());
            eventoDaSalvare.setMuseo(evento.getMuseo());
        }

        // ✅ Caricamento immagine se presente
        if (immagine != null && !immagine.isEmpty()) {
            String uploadDir = "C:\\Users\\182935\\Documents\\workspace-spring-tool-suite-4-4.28.1.RELEASE\\progettoPersonaleSIW-1\\uploads\\images\\";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {
                String nomeFile = UUID.randomUUID() + "_" + immagine.getOriginalFilename();
                immagine.transferTo(new File(uploadDir + nomeFile));
                eventoDaSalvare.setUrlImage("images/" + nomeFile); // ✅ CORRETTO
            } catch (IOException e) {
                model.addAttribute("errore", "Errore nel caricamento immagine: " + e.getMessage());
                model.addAttribute("evento", evento);
                return "formEvento";
            }
        }

        // 🖼️ Associa le opere selezionate
        List<Opera> opere = (opereIds != null) ? opereIds.stream()
                .map(id -> operaService.getOperaById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()) : new ArrayList<>();
        eventoDaSalvare.setOpere(opere);

        // 🕒 Gestione fasce orarie
        Map<String, Fascia> fasceEsistenti = new HashMap<>();
        List<Fascia> fasceAggiornate = new ArrayList<>();

        if (eventoDaSalvare.getFasceOrarie() != null) {
            for (Fascia f : eventoDaSalvare.getFasceOrarie()) {
                String key = f.getData() + "|" + f.getOrarioInizio();
                fasceEsistenti.put(key, f);
            }
        }

        Set<String> chiaviForm = new HashSet<>();
        if (fasceData != null) {
            for (int i = 0; i < fasceData.size(); i++) {
                LocalDate data = LocalDate.parse(fasceData.get(i));
                LocalTime ora = LocalTime.parse(fasceOrarioInizio.get(i));
                int capienza = fasceCapienza.get(i);

                String key = data + "|" + ora;
                chiaviForm.add(key);

                if (fasceEsistenti.containsKey(key)) {
                    Fascia esistente = fasceEsistenti.get(key);
                    esistente.setCapienzaMassima(capienza);
                    fasceAggiornate.add(esistente);
                } else {
                    Fascia nuova = new Fascia();
                    nuova.setData(data);
                    nuova.setOrarioInizio(ora);
                    nuova.setCapienzaMassima(capienza);
                    nuova.setEvento(eventoDaSalvare);
                    nuova.setPostiPrenotati(0);
                    nuova.setPrenotazioni(new ArrayList<>());
                    fasceAggiornate.add(nuova);
                }
            }
        }

        // 🔴 Rimuovi le fasce eliminate
        List<Fascia> fasceDaRimuovere = new ArrayList<>();
        for (Fascia esistente : eventoDaSalvare.getFasceOrarie()) {
            String key = esistente.getData() + "|" + esistente.getOrarioInizio();
            if (!chiaviForm.contains(key)) {
                fasceDaRimuovere.add(esistente);
            }
        }

        eventoDaSalvare.getFasceOrarie().clear();
        eventoDaSalvare.getFasceOrarie().addAll(fasceAggiornate);
        fasciaRepository.deleteAll(fasceDaRimuovere);

        // ✅ Salva evento
        eventoService.aggiungiEvento(eventoDaSalvare);

        return "redirect:/evento/" + eventoDaSalvare.getId();
    }

    // eliminazione
    @PostMapping("/evento/elimina/{id}")
    public String cancellaEvento(@PathVariable Long id) {
        Utente utente = utenteService.getUtenteAutenticato();
        if (!utenteService.isAdmin(utente)) {
            throw new RuntimeException("Accesso Negato");
        }

        eventoService.cancellaEvento(id, utente);
        return "redirect:/eventi";
    }

    @PostMapping("/evento/{eventoId}/rimuovi-opera/{operaId}")
    public ResponseEntity<?> rimuoviOperaDaEvento(@PathVariable Long eventoId, @PathVariable Long operaId) {
        try {
            eventoService.rimuoviOperaDaEvento(eventoId, operaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la rimozione");
        }
    }

}