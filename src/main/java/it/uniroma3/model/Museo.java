package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Museo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Il nome non può essere nullo")
    private String nome;

	@Size(max = 500, message = "La descrizione non può superare 500 caratteri")
    private String descrizione;

	@NotBlank(message = "L'indirizzo non può essere nullo")
    private String indirizzo;

	@NotNull(message = "Gli orari sono obbligatori")
    private String orariApertura;

	
	@OneToMany
	private List<Evento> eventi;
	@OneToMany
	private List<Opera> opere;

	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getOrariApertura() {
		return orariApertura;
	}
	public void setOrariApertura(String orariApertura) {
		this.orariApertura = orariApertura;
	}

	public void setEvento(Evento evento) {
		this.eventi.add(evento);
	}

	public List<Evento> getEventi() {
		return this.eventi;
	}

	public void setOpera(Opera opera) {
		this.opere.add(opera);
	}

	public List<Opera> getOpere() {
		return this.opere;
	}
}
