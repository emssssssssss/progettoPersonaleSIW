package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Artista {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Il nome non può essere nullo")
    private String nome;

	@Size(max = 700, message = "La biografia non può superare 700 caratteri")
    private String biografia;

	@NotNull(message = "L'anno è obbligatorio")
    private int annoNascita;

	@NotNull(message = "L'anno è obbligatorio")
	private int annoMorte;

	@NotBlank(message = "L'URL dell'immagine non può essere vuoto")
	@Size(max = 2048, message = "L'URL dell'immagine è troppo lungo")
	@Pattern(regexp = "^(http|https)://.*\\.(jpg|jpeg|png|gif)$", message = "L'URL deve essere valido e puntare a un'immagine (jpg, jpeg, png, gif)")
    private String immagineUrl;

    @OneToMany(mappedBy = "artista")
    private List<Opera> opere;

    @ManyToOne
    private Museo museo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
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

	public int getAnnoMorte() {
		return annoMorte;
	}

	public void setAnnoMorte(int annoMorte) {
		this.annoMorte = annoMorte;
	}

	@Override
	public boolean equals(Object o) {
    	if (this == o) return true; // stesso oggetto
    	if (o == null || getClass() != o.getClass()) return false; // null o classi diverse

   		Artista artista = (Artista) o; // cast sicuro

    	// se id è null in uno dei due, considerali diversi
    	if (id == null || artista.id == null) return false;

    	return id.equals(artista.id);
	}

	@Override
	public int hashCode() {
   		return id != null ? id.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return "Artista{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", annoNascita=" + annoNascita +
				", annoMorte=" + annoMorte +
				", immagineUrl='" + immagineUrl + '\'' +
				'}';
	}

}
