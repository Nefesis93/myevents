package it.myevents.myevents.pojos;

public class Tipologia {
	private String nome;

	public Tipologia(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Tipologia [nome=" + nome + "]";
	}
}