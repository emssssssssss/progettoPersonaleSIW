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

@Entity
public class DisponibilitaFascia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate data;
	private LocalTime orarioInizio;
	private int capienzaMassima;
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
