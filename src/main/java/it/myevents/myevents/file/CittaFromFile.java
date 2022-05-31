package it.myevents.myevents.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import it.myevents.myevents.pojos.Citta;
import it.myevents.myevents.pojos.Provincia;
import it.myevents.myevents.util.ReadFromFile;
import it.myevents.myevents.dao.ProvinciaDAO;

public class CittaFromFile extends ReadFromFile<Citta> {

    private static final Function<String, Optional<Citta>> PARSE = line -> {
        Optional<Citta> optional = Optional.empty();
        String[] csv = line.split(",");
        Optional<Provincia> p = new ProvinciaDAO().getBySigla(csv[1].replace("\n", ""));
        if (!p.isEmpty()) {
            Citta c = new Citta(csv[0].replace("'", "''"), p.get());
            optional = Optional.of(c);
        } else {
            System.out.println(ERROR + Arrays.toString(csv));
        }
        return optional;
    };

    private static final String FILE = "citta_finali.csv";

    public CittaFromFile() {
        super(FILE, PARSE);
    }
}