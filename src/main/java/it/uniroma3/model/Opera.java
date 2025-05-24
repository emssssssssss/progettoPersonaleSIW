package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titolo;
	private String descrizione;
	private int anno;
	private String immagineUrl;

	@ManyToOne
	private Artista artista;

	@ManyToOne
	private Museo museo;

	@ManyToMany
	private List<Evento> evento;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
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

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getImmagineUrl() {
		return immagineUrl;
	}

	public void setImmagineUrl(String immagineUrl) {
		this.immagineUrl = immagineUrl;
	}

}
