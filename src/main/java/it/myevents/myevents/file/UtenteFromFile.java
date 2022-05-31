package it.myevents.myevents.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.dao.CittaDAO;
import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Utente;
import it.myevents.myevents.util.ReadFromFile;

public class UtenteFromFile extends ReadFromFile<Utente> {

	private static final Function<String, Optional<Utente>> PARSE = line -> {
		Optional<Utente> optional = Optional.empty();
		CittaDAO cittaDAO = new CittaDAO();
		String[] csv = line.split(",");
		Optional<Citta> c = cittaDAO.getByName(csv[4].replace("\n", ""));
		if (!c.isEmpty()) {
			Utente u = new Utente(csv[0], csv[1], csv[2], csv[3], c.get());
			optional = Optional.of(u);
		} else {
			System.out.println(ERROR + Arrays.toString(csv));
		}
		return optional;
	};

	private static final String FILE = "utenti_finali.csv";

	public UtenteFromFile() {
		super(FILE, PARSE);
	}
}