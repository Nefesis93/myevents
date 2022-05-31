package it.myevents.myevents.file;

import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Tipologia;
import it.myevents.myevents.util.ReadFromFile;

public class TipologiaFromFile extends ReadFromFile<Tipologia> {

	private static final Function<String, Optional<Tipologia>> PARSE = line -> {
		return Optional.of(new Tipologia((line.replace("\n", ""))));
	};

	private static final String FILE = "tipologie.csv";

	public TipologiaFromFile() {
		super(FILE, PARSE);
	}

}