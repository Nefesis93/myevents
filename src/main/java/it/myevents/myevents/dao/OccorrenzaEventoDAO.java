package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Data;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.OccorrenzaEvento;
import it.myevents.myevents.util.DAO;

public class OccorrenzaEventoDAO extends DAO<OccorrenzaEvento> {

	private static final Function<ResultSet, Optional<OccorrenzaEvento>> GET_FROM_RESULT = resultSet -> {
		Optional<OccorrenzaEvento> optional = Optional.empty();
		try {
			Optional<Evento> e = new EventoDAO().getById(resultSet.getInt("evento"));
			if (!e.isEmpty()) {
				OccorrenzaEvento o = new OccorrenzaEvento(e.get(), new Data(resultSet.getDate("data").toString()));
				optional = Optional.of(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<OccorrenzaEvento, String> GET_INSERT_QUERY = occorrenzaEvento -> {
		String insert = "INSERT INTO occorrenza_evento (evento, data) values (~,~)";
		insert = insert.replaceFirst("~", occorrenzaEvento.getEvento().getId() + "");
		insert = insert.replaceFirst("~", wrapString(occorrenzaEvento.getData().toString()));
		return insert;
	};

	private static final String TABLE = "occorrenza_evento";

	public OccorrenzaEventoDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}
}