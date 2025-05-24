package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Artista {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String biografia;
    private int anno;
    private String immagineUrl;

    @OneToMany
    private List<Opera> opere;

    @ManyToOne
    private Museo museo;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getBiografia() {
		return this.biografia;
	}

	public void setBiografia(String bio) {
		this.biografia = bio;
	}

	public String getImmagineUrl() {
		return immagineUrl;
	}

	public void setImmagineUrl(String immagineUrl) {
		this.immagineUrl = immagineUrl;
	}

}
