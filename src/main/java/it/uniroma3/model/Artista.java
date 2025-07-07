package it.uniroma3.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@Max(value = 2025, message = "L'anno di nascita non può essere nel futuro")
	private int annoNascita;

	@Max(value = 2025, message = "L'anno di morte non può essere nel futuro")
	private Integer annoMorte;

	// Campo transient per file upload, non salvato nel DB
    @Transient
    private MultipartFile fileImage;

	@Column(name = "immagine_url")
	private String urlImage;

	@OneToMany(mappedBy = "artista",  cascade = CascadeType.REMOVE, orphanRemoval = true)
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Integer getAnnoMorte() {
		return annoMorte;
	}

	public void setAnnoMorte(Integer annoMorte) {
		this.annoMorte = annoMorte;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Artista))
			return false;
		Artista artista = (Artista) o;
		return id != null && id.equals(artista.getId());
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
				", immagineUrl='" + urlImage + '\'' +
				'}';
	}


	public MultipartFile getFileImage() {
        return fileImage;
    }
    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }

}
