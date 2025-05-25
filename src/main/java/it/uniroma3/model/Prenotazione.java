package it.uniroma3.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Utente utente;

	@ManyToOne
	private Fascia disponibilita;

	private int numeroPersone;

	@Enumerated(EnumType.STRING)
	private Stato stato;

	private LocalDate dataPrenotazione;

	public enum Stato {
		CONFERMATA,
		ANNULLATA
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setDisponibilita( Fascia disponibilita) {
		this.disponibilita = disponibilita;
	}

	public Fascia getDisponibilita() {
		return this.disponibilita;
	}

	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}
}
