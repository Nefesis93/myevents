package it.myevents.myevents.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.dao.EventoDAO;
import it.myevents.myevents.pojos.Data;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.OccorrenzaEvento;
import it.myevents.myevents.util.ReadFromFile;

public class OccorrenzaEventoFromFile extends ReadFromFile<OccorrenzaEvento> {

	private static final Function<String, Optional<OccorrenzaEvento>> PARSE = line -> {
		Optional<OccorrenzaEvento> optional = Optional.empty();
		String[] csv = line.split(",");
		Optional<Evento> e = new EventoDAO().getByName(csv[0]);
		if (!e.isEmpty()) {
			Data d = new Data(csv[1].replace("\n", ""));
			OccorrenzaEvento o = new OccorrenzaEvento(e.get(), d);
			optional = Optional.of(o);
		} else {
			System.out.println(ERROR + Arrays.toString(csv));
		}
		return optional;
	};

	private static final String FILE = "occorrenza_evento.csv";

	public OccorrenzaEventoFromFile() {
		super(FILE, PARSE);
	}
}