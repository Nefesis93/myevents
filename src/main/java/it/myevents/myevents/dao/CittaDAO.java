package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Provincia;
import it.myevents.myevents.util.DAO;

public class CittaDAO extends DAO<Citta> {

	private static final Function<ResultSet, Optional<Citta>> GET_FROM_RESULT = resultSet -> {
		Optional<Citta> optional = Optional.empty();
		try {
			Optional<Provincia> p = new ProvinciaDAO().getByNome(resultSet.getString("provincia"));
			if (!p.isEmpty()) {
				Citta c = new Citta(resultSet.getInt("id"), resultSet.getString("nome"), p.get());
				optional = Optional.of(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Citta, String> GET_INSERT_QUERY = citta -> {
		String insert = "INSERT INTO citta (nome, provincia) values (~,~)";
		insert = insert.replaceFirst("~", wrapString(citta.getNome()));
		insert = insert.replaceFirst("~", wrapString(citta.getProvincia().getNome().replace("'", "''")));
		return insert;
	};

	private static final String TABLE = "citta";

	public CittaDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Citta> getByName(String nome) {
		String query = "SELECT * FROM citta where nome = " + DAO.wrapString(nome.replace("'", "''"));
		return singleSelect(query);
	}

	public Optional<Citta> getById(int id) {
		String query = "SELECT * FROM citta where id = " + id;
		return singleSelect(query);
	}
}