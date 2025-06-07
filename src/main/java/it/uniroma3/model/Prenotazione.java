package it.uniroma3.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)  // impone che una prenotazione debba sempre essere associata a un utente, evitando utente_id null nel database.
	private Utente utente;

	@ManyToOne(optional = false)
	private Fascia fascia;

	private int numeroBiglietti;

	@Min(value = 1, message = "Devi prenotare minimo 1 biglietto")
	@Max(value = 8, message = "Puoi prenotare al massimo 8 biglietti")
	private int numeroPersone;

	@Enumerated(EnumType.STRING)
	private Stato stato;

	@FutureOrPresent(message = "La data deve essere oggi o futura")
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

	public void setFascia( Fascia fascia) {
		this.fascia = fascia;
	}

	public Fascia getFascia() {
		return this.fascia;
	}

	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public int getNumeroBiglietti() {
		return numeroBiglietti;
	}

	public void setNumeroBiglietti(int numeroBiglietti) {
		this.numeroBiglietti = numeroBiglietti;
	}



	//per evitare duplicati nelle collezioni
	@Override
	public boolean equals(Object o) {
    	if (this == o) return true;
    	if (o == null || getClass() != o.getClass()) return false;
    	Prenotazione that = (Prenotazione) o;
    	return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
    	return getClass().hashCode();
	}

	@Override
	public String toString() {
    	return "Prenotazione{" +
            	"id=" + id +
           		", utente=" + (utente != null ? utente.getId() : null) +
            	", fascia=" + (fascia != null ? fascia.getId() : null) +
           		", numeroPersone=" + numeroPersone +
            	", stato=" + stato +
            	", dataPrenotazione=" + dataPrenotazione +
            	'}';
	}


}
