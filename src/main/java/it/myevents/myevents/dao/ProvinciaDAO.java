package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Provincia;
import it.myevents.myevents.util.DAO;

public class ProvinciaDAO extends DAO<Provincia> {

	private static final Function<ResultSet, Optional<Provincia>> GET_FROM_RESULT = resultSet -> {
		Optional<Provincia> optional = Optional.empty();
		try {
			Provincia p = new Provincia(resultSet.getString("nome"), resultSet.getString("sigla"),
					resultSet.getString("regione"));
			optional = Optional.of(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Provincia, String> GET_INSERT_QUERY = provincia -> {
		String insert = "INSERT INTO provincia (nome, sigla, regione) values (~,~,~)";
		insert = insert.replaceFirst("~", wrapString(provincia.getNome().replace("'", "''")));
		insert = insert.replaceFirst("~", wrapString(provincia.getSigla()));
		insert = insert.replaceFirst("~", wrapString(provincia.getRegione().replace("'", "''")));
		return insert;
	};

	private static final String TABLE = "provincia";

	public ProvinciaDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Provincia> getByNome(String nome) {
		String query = "SELECT * FROM provincia where nome = " + wrapString(nome.replace("'", "''"));
		return singleSelect(query);
	}

	public Optional<Provincia> getBySigla(String sigla) {
		String query = "SELECT * FROM provincia where sigla = " + wrapString(sigla);
		return singleSelect(query);
	}
}