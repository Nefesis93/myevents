package it.myevents.myevents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Location;
import it.myevents.myevents.util.DAO;

public class LocationDAO extends DAO<Location> {

	private static final Function<ResultSet, Optional<Location>> GET_FILE_FROM_RESULT = resultSet -> {
		Optional<Location> optional = Optional.empty();
		try {
			Optional<Citta> optionalCitta = new CittaDAO().getById(resultSet.getInt("citta"));
			if (!optionalCitta.isEmpty()) {
				Location l = new Location(resultSet.getInt("id"), resultSet.getString("nome"),
						resultSet.getString("via"),
						resultSet.getInt("civico"), resultSet.getInt("cap"), optionalCitta.get());
				optional = Optional.of(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optional;
	};

	private static final Function<Location, String> GET_INSERT_QUERY = location -> {
		String query = "INSERT INTO location (nome, via, civico, cap, citta) values (~,~,~,~,~)";
		query = query.replaceFirst("~", wrapString(location.getNome()));
		query = query.replaceFirst("~", wrapString(location.getVia().replace("'", "''")));
		query = query.replaceFirst("~", location.getCivico() + "");
		query = query.replaceFirst("~", location.getCap() + "");
		query = query.replaceFirst("~", location.getCitta().getId() + "");
		return query;
	};

	private static final String TABLE = "location";

	public LocationDAO() {
		super(TABLE, GET_FILE_FROM_RESULT, GET_INSERT_QUERY);
	}

	public Optional<Location> getById(int id) {
		String query = "SELECT * FROM location where id = " + id;
		return singleSelect(query);
	}
}