package it.uniroma3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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

	@NotNull(message = "La data di inizio è obbligatoria")
	private LocalDate dataInizio;

	@NotNull(message = "La data di fine è obbligatoria")
	@Future(message = "La data di fine deve essere nel futuro")
	private LocalDate dataFine;

	@Column(name = "immagine_url")
	private String urlImage;

	@ManyToOne
	private Museo museo;

	@ManyToMany(mappedBy = "eventi")
	private List<Opera> opere = new ArrayList<>();

	@OneToMany(mappedBy = "evento")
	private List<Fascia> fasceOrarie = new ArrayList<>();

	// --- Getters e Setters ---

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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Museo getMuseo() {
		return museo;
	}

	public void setMuseo(Museo museo) {
		this.museo = museo;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}

	public void addOpera(Opera opera) {
		this.opere.add(opera);
	}

	public List<Fascia> getFasceOrarie() {
		return fasceOrarie;
	}

	public void setFasceOrarie(List<Fascia> fasceOrarie) {
		this.fasceOrarie = fasceOrarie;
	}

	public void addFasciaOraria(Fascia fascia) {
		this.fasceOrarie.add(fascia);
	}

	// --- equals, hashCode e toString ---

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Evento evento = (Evento) o;

		return id != null ? id.equals(evento.id) : evento.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Evento{" +
				"id=" + id +
				", titolo='" + titolo + '\'' +
				", descrizione='" + descrizione + '\'' +
				", dataInizio=" + dataInizio +
				", dataFine=" + dataFine +
				", urlImage='" + urlImage + '\'' +
				'}';
	}
}
