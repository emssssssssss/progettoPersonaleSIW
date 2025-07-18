package it.uniroma3.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Il titolo non può essere nullo")
	private String titolo;

	@Size(max = 500, message = "La descrizione non può superare 500 caratteri")
	private String descrizione;

	@NotNull(message = "L'anno è obbligatorio")
	private int anno;

	@Column(name = "immagine_url")
	private String urlImage;

    // Campo transient per file upload, non salvato nel DB
    @Transient
    private MultipartFile fileImage;

	@ManyToOne
	private Artista artista;

	@ManyToOne
	private Museo museo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
    name = "opera_eventi",
    joinColumns = @JoinColumn(name = "opere_id"),
    inverseJoinColumns = @JoinColumn(name = "eventi_id"))
	private List<Evento> eventi;

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

	 public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Artista getArtista() {
		return this.artista;
	}

	public void setMuseo(Museo museo) {
		this.museo = museo;
	}

	public Museo getMuseo() {
		return this.museo;
	}

	public void setEventi (List<Evento> eventi) {
		this.eventi = eventi;
	}

	public void setEvento(Evento evento) {
		this.eventi.add(evento);
	}

	public List<Evento> getEventi() {
		return this.eventi;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Opera opera = (Opera) o;
		return id != null && id.equals(opera.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Opera{" +
				"id=" + id +
				", titolo='" + titolo + '\'' +
				", descrizione='" + descrizione + '\'' +
				", anno=" + anno +
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
