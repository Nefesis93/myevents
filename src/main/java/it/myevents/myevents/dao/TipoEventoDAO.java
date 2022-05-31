package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.Tipologia;
import it.myevents.myevents.pojos.TipoEvento;
import it.myevents.myevents.util.DAO;

public class TipoEventoDAO extends DAO<TipoEvento> {

	private static final Function<ResultSet, Optional<TipoEvento>> GET_FROM_RESULT = resultSet -> {
		Optional<TipoEvento> optional = Optional.empty();
		try {
			Optional<Evento> e = new EventoDAO().getById(resultSet.getInt("evento"));
			Optional<Tipologia> t = new TipologiaDAO().getByName(resultSet.getString("tipologia"));
			if (!e.isEmpty() && !t.isEmpty()) {
				TipoEvento te = new TipoEvento(e.get(), t.get());
				optional = Optional.of(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<TipoEvento, String> GET_INSERT_QUERY = tipologiaEvento -> {
		String insert = "INSERT INTO tipo_evento (evento, tipologia) values (~,~)";
		insert = insert.replaceFirst("~", tipologiaEvento.getEvento().getId() + "");
		insert = insert.replaceFirst("~", wrapString(tipologiaEvento.getTipologia().getNome().replace("'", "''")));
		return insert;
	};

	private static final String TABLE = "tipo_evento";

	public TipoEventoDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}
}