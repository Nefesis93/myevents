package it.myevents.myevents.pojos;

public class OccorrenzaEvento {
	private Evento evento;
	private Data data;

	public OccorrenzaEvento(Evento evento, Data data) {
		this.evento = evento;
		this.data = data;

	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "OccorrenzaEvento = [evento=" + evento.getId() + ", data=" + data + "]";
	}
}