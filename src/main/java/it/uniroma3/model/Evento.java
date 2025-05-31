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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Il titolo non può essere nullo")
    private String titolo;

	@Size(max = 500, message = "La descrizione non può superare 500 caratteri")
    private String descrizione;

	@NotNull(message = "La data di inizio è obbligatorio")
    private LocalDate dataInizio;

	@NotNull(message = "L'anno è obbligatorio")
	@Future(message = "la data di fine deve essere nel futuro")
    private LocalDate dataFine;

	
    @ManyToOne
    private Museo museo;
    @ManyToMany(mappedBy = "eventi")
    private List<Opera> opere;
	@OneToMany(mappedBy = "evento")
	private List<Fascia> fasceOrarie;
	
	
	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}


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

	public void setFasciaOraria(Fascia fascia) {
		this.fasceOrarie.add(fascia);
	}

	public List<Fascia> getFascieOrarie() {
		return this.fasceOrarie;
	}

	public void setMuseo(Museo museo) {
		this.museo = museo;
	}

	public Museo getMuseo() {
		return this.museo;
	}

	public void setOpera(Opera opera) {
		this.opere.add(opera);
	}

	public List<Opera> getOpere() {
		return this.opere;
	}
}
