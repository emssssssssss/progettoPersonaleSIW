package it.uniroma3.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Fascia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "La data non può essere nulla")
    @Future(message = "La data deve essere nel futuro")
	private LocalDate data;

	
    @NotNull(message = "L'orario di inizio non può essere nullo")
	private LocalTime orarioInizio;

	@Min(value = 1, message = "La capienza massima deve essere almeno 1")
	private int capienzaMassima;

	@Min(value = 0, message = "I posti prenotati non possono essere negativi")
	private int postiPrenotati = 0;

	
	@ManyToOne
	private Museo museo;
	@OneToMany
	private List<Prenotazione> prenotazioni;
	@ManyToOne
	private Evento evento;

	public LocalTime getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(LocalTime orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getCapienzaMassima() {
		return capienzaMassima;
	}

	public void setCapienzaMassima(int capienzaMassima) {
		this.capienzaMassima = capienzaMassima;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	public void setPostiPrenotati(int posti) {
		this.postiPrenotati = posti;
	}

	public void addPrenotazione(Prenotazione pren) {
		this.prenotazioni.add(pren);
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}

	public void setMuseo(Museo museo) {
		this.museo = museo;
	}

	public Museo getMuseo() {
		return this.museo;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return this.evento;
	}

}
