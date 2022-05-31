package it.myevents.myevents.pojos;

public class TipoEvento {

	private Evento evento;
	private Tipologia tipologia;

	public TipoEvento(Evento evento, Tipologia tipologia) {
		this.evento = evento;
		this.tipologia = tipologia;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}

	@Override
	public String toString() {
		return "TipologiaEvento [evento=" + evento + ", tipologia=" + tipologia + "]";
	}
}