package it.myevents.myevents.file;

import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Provincia;
import it.myevents.myevents.util.ReadFromFile;

public class ProvinciaFromFile extends ReadFromFile<Provincia> {

    private static final Function<String, Optional<Provincia>> PARSE = line -> {
        String[] csv = line.split(",");
        return Optional.of(new Provincia(csv[0], csv[1], csv[2].replace("\n", "")));
    };

    private static final String FILE = "province_finali.csv";

    public ProvinciaFromFile() {
        super(FILE, PARSE);
    }
}