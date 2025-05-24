package it.uniroma3.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
public class Utente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String password;

	@OneToMany
	private List<Prenotazione> prenotazioni;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public enum Ruolo {
        VISITATORE,
        STAFF
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

	public void addPrenotazione(Prenotazione pren) {
		this.prenotazioni.add(pren);
	}

	public void addPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public List<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}
}

