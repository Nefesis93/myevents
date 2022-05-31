package it.myevents.myevents.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.dao.CittaDAO;
import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Location;
import it.myevents.myevents.util.ReadFromFile;

public class LocationFromFile extends ReadFromFile<Location> {

	private static final Function<String, Optional<Location>> PARSE = line -> {
		Optional<Location> optional = Optional.empty();
		CittaDAO cittaDAO = new CittaDAO();
		String[] csv = line.split(",");
		Optional<Citta> c = cittaDAO.getByName(csv[4].replace("\n", ""));
		if (!c.isEmpty()) {
			Location l = new Location(csv[0], csv[1], Integer.parseInt(csv[2]), Integer.parseInt(csv[3]), c.get());
			optional = Optional.of(l);
		} else {
			System.out.println(ERROR + Arrays.toString(csv));
		}
		return optional;
	};

	private static final String FILE = "location_finali.csv";

	public LocationFromFile() {
		super(FILE, PARSE);
	}
}