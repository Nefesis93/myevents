package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Utente;
import it.myevents.myevents.util.DAO;

public class UtenteDAO extends DAO<Utente> {

	private static final Function<ResultSet, Optional<Utente>> GET_FROM_RESULT = resultSet -> {
		Optional<Utente> optional = Optional.empty();
		try {
			Optional<Citta> optionalCitta = new CittaDAO().getById(resultSet.getInt("citta"));
			if (!optionalCitta.isEmpty()) {
				Utente u = new Utente(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"),
						resultSet.getString("nome"),
						resultSet.getString("cognome"), optionalCitta.get());
				optional = Optional.of(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Utente, String> GET_INSERT_QUERY = utente -> {
		String insert = "INSERT INTO utente (email, password, nome, cognome, citta) values (~,~,~,~,~)";
		insert = insert.replaceFirst("~", wrapString(utente.getEmail()));
		insert = insert.replaceFirst("~", wrapString(utente.getPassword()));
		insert = insert.replaceFirst("~", wrapString(utente.getNome()));
		insert = insert.replaceFirst("~", wrapString(utente.getCognome()));
		insert = insert.replaceFirst("~", utente.getCitta().getId() + "");
		return insert;
	};

	private static final String TABLE = "utente";

	public UtenteDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Utente> getById(int id) {
		String query = "SELECT * FROM utente where id = " + id;
		return singleSelect(query);
	}
}
