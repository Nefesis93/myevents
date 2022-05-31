package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.Location;
import it.myevents.myevents.pojos.Utente;
import it.myevents.myevents.util.DAO;

public class EventoDAO extends DAO<Evento> {

	private static final Function<ResultSet, Optional<Evento>> GET_FROM_RESULT = resultSet -> {
		Optional<Evento> optional = Optional.empty();
		try {
			Optional<Utente> u = new UtenteDAO().getById(resultSet.getInt("organizzatore"));
			Optional<Location> l = new LocationDAO().getById(resultSet.getInt("location"));
			if (!u.isEmpty() && !l.isEmpty()) {
				Evento e = new Evento(resultSet.getInt("id"), resultSet.getString("nome"),
						resultSet.getString("descrizione"), resultSet.getString("immagine"), resultSet.getTime("ora"),
						u.get(), l.get());
				optional = Optional.of(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Evento, String> GET_INSERT_QUERY = evento -> {
		String insert = "INSERT INTO evento (nome, descrizione, immagine, ora, organizzatore, location) values (~,~,~,~,~,~)";
		insert = insert.replaceFirst("~", wrapString(evento.getNome()));
		insert = insert.replaceFirst("~", wrapString(evento.getDescrizione()));
		insert = insert.replaceFirst("~", wrapString(evento.getImagePath()));
		insert = insert.replaceFirst("~", wrapString(evento.getOra().toString()));
		insert = insert.replaceFirst("~", evento.getOrganizzatore().getId() + "");
		insert = insert.replaceFirst("~", evento.getLocation().getId() + "");
		return insert;
	};

	private static final String TABLE = "evento";

	public EventoDAO() {
		super(TABLE, GET_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Evento> getById(int id) {
		String query = "SELECT * FROM evento where id = " + id;
		return singleSelect(query);
	}

	public Optional<Evento> getByName(String nome) {
		String query = "SELECT * FROM evento where nome = '" + nome.replace("'", "''") + "'";
		return singleSelect(query);
	}
}
