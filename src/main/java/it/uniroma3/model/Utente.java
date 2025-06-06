package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
public class Utente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Il nome è obbligatorio")
    private String nome;

	@Email(message = "Inserire email valida")
	@NotBlank(message = "L'email è obbligatoria")
    @Column(unique = true)
    private String email;

	@NotBlank(message = "Obbligatorio inserire password")
	@Size(min=6, message = "La password deve contenere almeno 6 caratteri ")
    private String password;

	@OneToMany(mappedBy = "utente")
	private List<Prenotazione> prenotazioni;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public enum Ruolo {
        VISITATORE,
        STAFF
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}

	public void addPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}
}

