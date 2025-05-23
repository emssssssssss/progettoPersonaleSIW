package it.uniroma3.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    @ManyToOne
    private Museo museo;
    @ManyToMany
    private List<Opera> opere;
	@OneToMany
	private List<DisponibilitaFascia> fasceOrarie;


	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public void setFasciaOraria(DisponibilitaFascia fascia) {
		this.fasceOrarie.add(fascia);
	}

	public void setFascieOrarie (List<DisponibilitaFascia> fascie) {
		this.fasceOrarie = fascie;
	}

	public List<DisponibilitaFascia> getFascieOrarie() {
		return this.fasceOrarie;
	}
}
