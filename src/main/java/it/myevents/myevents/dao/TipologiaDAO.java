package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Tipologia;
import it.myevents.myevents.util.DAO;

public class TipologiaDAO extends DAO<Tipologia> {

	private static final Function<ResultSet, Optional<Tipologia>> GET_FROM_RESULT = resultSet -> {
		Optional<Tipologia> optional = Optional.empty();
		try {
			Tipologia t = new Tipologia(resultSet.getString("nome"));
			optional = Optional.of(t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Tipologia, String> GET_INSERT_QUERY = tipologia -> {
		String insert = "INSERT INTO tipologia (nome) values (~)";
		insert = insert.replaceFirst("~", wrapString(tipologia.getNome().replace("'", "''")));
		return insert;
	};

	private static final String TABLE = "tipologia";

	public TipologiaDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Tipologia> getByName(String nome) {
		String query = "SELECT * FROM tipologia where nome = '" + nome.replace("'", "''") + "'";
		return singleSelect(query);
	}
}