package it.uniroma3.controller;

//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.UUID;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import it.uniroma3.model.Evento;
import it.uniroma3.model.Fascia;
import it.uniroma3.model.Opera;
import it.uniroma3.model.Utente;
import it.uniroma3.service.EventoService;
import it.uniroma3.service.OperaService;
import it.uniroma3.service.UtenteService;
import jakarta.validation.Valid;
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

    @Value("${app.upload.dir}")
    private String uploadDir;

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
        List<Opera> tutteLeOpere = operaService.getAllOpere();
        model.addAttribute("evento", new Evento());
        model.addAttribute("tutteLeOpere", tutteLeOpere);
        return "formEvento";
    }

    // mostra form di modifica di un evento esistente
    @GetMapping("/evento/modifica/{id}")
    public String mostraFormModificaEvento(@PathVariable Long id, Model model) {
        Evento evento = eventoService.getEventoById(id);
        model.addAttribute("evento", evento);
        model.addAttribute("tutteLeOpere", operaService.getAllOpere()); // se serve
        return "formEvento"; // il nome del tuo HTML
    }

    @PostMapping("/evento/salva")
    public String salvaEvento(
            @Valid @ModelAttribute("evento") Evento evento,
            BindingResult bindingResult,
            @RequestParam(value = "fileImage", required = false) MultipartFile fileImage,
            @RequestParam(value = "opereIds", required = false) List<Long> opereIds,
            Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tutteLeOpere", operaService.getAllOpere());
            return "formEvento";
        }

        // 1) Recupero o nuovo
        Evento daSalvare = evento.getId() != null
            ? eventoService.getEventoById(evento.getId())
            : new Evento();

        // 2) Campi semplici
        daSalvare.setTitolo(evento.getTitolo());
        daSalvare.setDescrizione(evento.getDescrizione());
        daSalvare.setDataInizio(evento.getDataInizio());
        daSalvare.setDataFine(evento.getDataFine());
        daSalvare.setMuseo(evento.getMuseo());

        // 3) Fasce orarie
        daSalvare.getFasceOrarie().clear();
        if (evento.getFasceOrarie() != null) {
            for (Fascia f : evento.getFasceOrarie()) {
                f.setEvento(daSalvare);
                daSalvare.addFasciaOraria(f);
            }
        }

        // 4) Upload immagine
        if (fileImage != null && !fileImage.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String filename = UUID.randomUUID() + "_" + StringUtils.cleanPath(fileImage.getOriginalFilename());
            fileImage.transferTo(uploadPath.resolve(filename).toFile());
            daSalvare.setUrlImage(filename);
        }

        // 5) Many‑to‑many Opere
        List<Opera> scelte = new ArrayList<>();
        if (opereIds != null) {
            for (Long oid : opereIds) {
                operaService.getOperaById(oid).ifPresent(scelte::add);
            }
        }
        daSalvare.setOpere(scelte);

        // 6) Salvo
        eventoService.aggiungiEvento(daSalvare);
        return "redirect:/evento/" + daSalvare.getId();
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