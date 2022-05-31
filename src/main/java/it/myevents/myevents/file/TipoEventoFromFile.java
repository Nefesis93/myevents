package it.myevents.myevents.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.dao.EventoDAO;
import it.myevents.myevents.dao.TipologiaDAO;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.TipoEvento;
import it.myevents.myevents.pojos.Tipologia;
import it.myevents.myevents.util.ReadFromFile;

public class TipoEventoFromFile extends ReadFromFile<TipoEvento> {

	private static final Function<String, Optional<TipoEvento>> PARSE = line -> {
		Optional<TipoEvento> optional = Optional.empty();
		String[] csv = line.split(",");
		Optional<Evento> e = new EventoDAO().getByName(csv[0]);
		Optional<Tipologia> t = new TipologiaDAO().getByName(csv[1].replace("\n", ""));
		if (!e.isEmpty() && !t.isEmpty()) {
			TipoEvento o = new TipoEvento(e.get(), t.get());
			optional = Optional.of(o);
		} else {
			System.out.println(ERROR + Arrays.toString(csv));
		}
		return optional;
	};

	private static final String FILE = "tipo_evento.csv";

	public TipoEventoFromFile() {
		super(FILE, PARSE);
	}
}