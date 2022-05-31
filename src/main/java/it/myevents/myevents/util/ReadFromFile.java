package it.myevents.myevents.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class ReadFromFile<T> {

	private static final String PATH = "files\\";
	protected static final String ERROR = "Non è stato possibile trovare una o più chiavi esterne per l'inserimento di ";

	private String file;
	private Function<String, Optional<T>> parse;

	public ReadFromFile(String file, Function<String, Optional<T>> parse) {
		this.file = file;
		this.parse = parse;
	}

	public List<T> read() {
		List<T> list = new LinkedList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(PATH + file, StandardCharsets.UTF_8));
			System.out.println("file " + file + " aperto correttamente");
			try {
				System.out.println("inizio lettura file...");
				String line = br.readLine();
				while (line != null) {
					Optional<T> optional = parse.apply(line);
					if (!optional.isEmpty())
						list.add(optional.get());
					line = br.readLine();
				}
			} catch (IOException e) {
				System.out.println("errore nell'apertura del file");
				e.printStackTrace();
			} finally {
				try {
					br.close();
					System.out.println("file chiuso correttamente");
				} catch (IOException e) {
					System.out.println("errore nella chiusura del file");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("il file richiesto non è stato trovato o non è un file UTF-8");
			e.printStackTrace();
		}
		return list;
	}
}