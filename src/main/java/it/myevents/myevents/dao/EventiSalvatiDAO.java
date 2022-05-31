package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.EventiSalvati;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.Utente;
import it.myevents.myevents.util.DAO;

public class EventiSalvatiDAO extends DAO<EventiSalvati> {

	private static final Function<ResultSet, Optional<EventiSalvati>> GET_FROM_RESULT = resultSet -> {
		Optional<EventiSalvati> optional = Optional.empty();
		try {
			Optional<Evento> e = new EventoDAO().getById(resultSet.getInt("evento"));
			Optional<Utente> u = new UtenteDAO().getById(resultSet.getInt("utente"));
			if (!e.isEmpty() && !u.isEmpty()) {
				EventiSalvati es = new EventiSalvati(u.get(), e.get());
				optional = Optional.of(es);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<EventiSalvati, String> GET_INSERT_QUERY = eventiSalvati -> {
		String insert = "INSERT INTO eventi_salvati (evento, utente) values (~,~)";
		insert = insert.replaceFirst("~", eventiSalvati.getEvento().getId() + "");
		insert = insert.replaceFirst("~", eventiSalvati.getUtente().getId() + "");
		return insert;
	};

	private static final String TABLE = "eventi_salvati";

	public EventiSalvatiDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}
}