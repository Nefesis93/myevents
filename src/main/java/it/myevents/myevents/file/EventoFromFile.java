package it.myevents.myevents.file;

import java.sql.Time;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.dao.LocationDAO;
import it.myevents.myevents.dao.UtenteDAO;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.Location;
import it.myevents.myevents.pojos.Utente;
import it.myevents.myevents.util.ReadFromFile;

public class EventoFromFile extends ReadFromFile<Evento> {

	private static final Function<String, Optional<Evento>> PARSE = line -> {
		Optional<Evento> optional = Optional.empty();
		String[] csv = line.split(",");
		Optional<Location> l = new LocationDAO().getRandom();
		Optional<Utente> u = new UtenteDAO().getRandom();
		if (!l.isEmpty() && !u.isEmpty()) {
			String[] ora = csv[2].split(":");
			Time t = new Time(Integer.parseInt(ora[0]), Integer.parseInt(ora[1]), 00);
			String descrizione = csv[1].replace("-", ",");
			descrizione = descrizione.replace("'", "''");
			String nome = csv[0].replace("'", "''");
			Evento e = new Evento(nome, descrizione, csv[3].replace("\n", ""), t,
					new UtenteDAO().getRandom().get(), new LocationDAO().getRandom().get());
			optional = Optional.of(e);
		} else {
			System.out.println(ERROR + Arrays.toString(csv));
		}
		return optional;
	};

	private static final String FILE = "eventi_finali.csv";

	public EventoFromFile() {
		super(FILE, PARSE);
	}
}