package it.myevents.myevents.pojos;

import java.sql.Time;

public class Evento {
	private int id;
	private String nome;
	private String descrizione;
	private String imagePath;
	private Time ora;
	private Utente organizzatore;
	private Location location;

	public Evento(int id, String nome, String descrizione, String imagePath, Time ora, Utente organizzatore,
			Location location) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.imagePath = imagePath;
		this.ora = ora;
		this.organizzatore = organizzatore;
		this.location = location;
	}

	public Evento(String nome, String descrizione, String imagePath, Time ora, Utente organizzatore,
			Location location) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.imagePath = imagePath;
		this.ora = ora;
		this.organizzatore = organizzatore;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public Utente getOrganizzatore() {
		return organizzatore;
	}

	public void setOrganizzatore(Utente organizzatore) {
		this.organizzatore = organizzatore;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Evento [nome=" + nome + ", location=" + location + ", organizzatore=" + organizzatore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + id;
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ora == null) ? 0 : ora.hashCode());
		result = prime * result + ((organizzatore == null) ? 0 : organizzatore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ora == null) {
			if (other.ora != null)
				return false;
		} else if (!ora.equals(other.ora))
			return false;
		if (organizzatore == null) {
			if (other.organizzatore != null)
				return false;
		} else if (!organizzatore.equals(other.organizzatore))
			return false;
		return true;
	}

}