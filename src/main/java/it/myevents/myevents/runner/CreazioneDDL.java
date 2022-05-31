package it.myevents.myevents.runner;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.myevents.myevents.util.ConnectionFactory;

@Component
public class CreazioneDDL implements CommandLineRunner {
	private static final String PROVINCIA = "CREATE TABLE IF NOT EXISTS public.provincia (" +
			"nome character varying(40) NOT NULL, " +
			"sigla character varying(2) NOT NULL, " +
			"regione character varying(40) NOT NULL, " +
			"PRIMARY KEY (nome), " +
			"CONSTRAINT sigla UNIQUE (sigla));";

	private static final String CITTA = "CREATE TABLE IF NOT EXISTS public.citta (" +
			"id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ), " +
			"nome character varying(40) NOT NULL, " +
			"provincia character varying(40) NOT NULL, " +
			"PRIMARY KEY (id), " +
			"CONSTRAINT provincia FOREIGN KEY (provincia) REFERENCES public.provincia (nome) MATCH SIMPLE "
			+
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	private static final String LOCATION = "CREATE TABLE IF NOT EXISTS public.location (" +
			"id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ), " +
			"nome character varying(40) NOT NULL, " +
			"via character varying(60) NOT NULL, " +
			"civico integer NOT NULL, " +
			"cap integer NOT NULL, " +
			"citta integer NOT NULL, " +
			"PRIMARY KEY (id), " +
			"CONSTRAINT citta FOREIGN KEY (citta) REFERENCES public.citta (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION, " +
			"CONSTRAINT cap CHECK (cap > 0 and cap < 100000) NOT VALID, " +
			"CONSTRAINT civico CHECK (civico > 0 and civico < 10000) NOT VALID);";

	private static final String UTENTE = "CREATE TABLE IF NOT EXISTS public.utente (" +
			"id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ), " +
			"email character varying(40) NOT NULL, " +
			"password character varying(30) NOT NULL, " +
			"nome character varying(40) NOT NULL, " +
			"cognome character varying(40) NOT NULL, " +
			"citta integer NOT NULL, " +
			"PRIMARY KEY (id), " +
			"CONSTRAINT email UNIQUE (email), " +
			"CONSTRAINT citta FOREIGN KEY (citta) REFERENCES public.citta (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	private static final String EVENTO = "CREATE TABLE IF NOT EXISTS public.evento (" +
			"id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ), " +
			"nome character varying(60) NOT NULL, " +
			"descrizione character varying(500) NOT NULL, " +
			"immagine character varying(100) NOT NULL, " +
			"ora time without time zone NOT NULL, " +
			"organizzatore integer NOT NULL, " +
			"location integer NOT NULL, " +
			"PRIMARY KEY (id), " +
			"CONSTRAINT organizzatore FOREIGN KEY (organizzatore) REFERENCES public.utente (id) MATCH SIMPLE "
			+
			"ON UPDATE NO ACTION ON DELETE NO ACTION, " +
			"CONSTRAINT location FOREIGN KEY (location) REFERENCES public.location (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	private static final String OCCORRENZA_EVENTO = "CREATE TABLE IF NOT EXISTS public.occorrenza_evento (" +
			"evento integer NOT NULL, " +
			"data date NOT NULL, " +
			"PRIMARY KEY (evento, data), " +
			"CONSTRAINT evento FOREIGN KEY (evento) REFERENCES public.evento (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	private static final String EVENTI_SALVATI = "CREATE TABLE IF NOT EXISTS public.eventi_salvati (" +
			"utente integer NOT NULL, " +
			"evento integer NOT NULL, " +
			"PRIMARY KEY (utente, evento), " +
			"CONSTRAINT utente FOREIGN KEY (utente) REFERENCES public.utente (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION, " +
			"CONSTRAINT evento FOREIGN KEY (evento) REFERENCES public.evento (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	private static final String TIPOLOGIA = "CREATE TABLE IF NOT EXISTS public.tipologia (" +
			"nome character varying(40) NOT NULL, " +
			"PRIMARY KEY (nome));";

	private static final String TIPO_EVENTO = "CREATE TABLE IF NOT EXISTS public.tipo_evento(" +
			"evento integer NOT NULL, " +
			"tipologia character varying(40) NOT NULL, " +
			"PRIMARY KEY (evento, tipologia), " +
			"CONSTRAINT evento FOREIGN KEY (evento) REFERENCES public.evento (id) MATCH SIMPLE " +
			"ON UPDATE NO ACTION ON DELETE NO ACTION, " +
			"CONSTRAINT tipologia FOREIGN KEY (tipologia) REFERENCES public.tipologia (nome) MATCH SIMPLE "
			+
			"ON UPDATE NO ACTION ON DELETE NO ACTION);";

	@Override
	public void run(String... args) throws Exception {
		PreparedStatement statement = null;
		try {
			// creo provincia
			statement = ConnectionFactory.getConnection().prepareStatement(PROVINCIA);
			statement.executeUpdate();

			// creo città
			statement = ConnectionFactory.getConnection().prepareStatement(CITTA);
			statement.executeUpdate();

			// creo location
			statement = ConnectionFactory.getConnection().prepareStatement(LOCATION);
			statement.executeUpdate();

			// creo utente
			statement = ConnectionFactory.getConnection().prepareStatement(UTENTE);
			statement.executeUpdate();

			// creo evento
			statement = ConnectionFactory.getConnection().prepareStatement(EVENTO);
			statement.executeUpdate();

			// creo occorrenza evento
			statement = ConnectionFactory.getConnection().prepareStatement(OCCORRENZA_EVENTO);
			statement.executeUpdate();

			// creo eventi salvati
			statement = ConnectionFactory.getConnection().prepareStatement(EVENTI_SALVATI);
			statement.executeUpdate();
			;

			// creo tipologia
			statement = ConnectionFactory.getConnection().prepareStatement(TIPOLOGIA);
			statement.executeUpdate();

			// creo tipo_evento
			statement = ConnectionFactory.getConnection().prepareStatement(TIPO_EVENTO);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Errore nella creazione di una delle tabelle");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}
	}
}