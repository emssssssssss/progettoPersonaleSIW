package it.uniroma3.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DisponibilitaBiglietti {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate data;
	private LocalTime orarioInizio;
	private int capienzaMassima;
	private int postiPrenotati;

	@ManyToOne
	private Museo museo;

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

	public void setPostiPrenotati(int postiPrenotati) {
		this.postiPrenotati = postiPrenotati;
	}

}
