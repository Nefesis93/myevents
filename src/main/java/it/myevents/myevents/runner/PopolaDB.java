package it.myevents.myevents.runner;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.myevents.myevents.dao.CittaDAO;
import it.myevents.myevents.dao.EventiSalvatiDAO;
import it.myevents.myevents.dao.EventoDAO;
import it.myevents.myevents.dao.LocationDAO;
import it.myevents.myevents.dao.OccorrenzaEventoDAO;
import it.myevents.myevents.dao.ProvinciaDAO;
import it.myevents.myevents.dao.TipologiaDAO;
import it.myevents.myevents.dao.TipoEventoDAO;
import it.myevents.myevents.dao.UtenteDAO;
import it.myevents.myevents.file.CittaFromFile;
import it.myevents.myevents.file.EventoFromFile;
import it.myevents.myevents.file.LocationFromFile;
import it.myevents.myevents.file.OccorrenzaEventoFromFile;
import it.myevents.myevents.file.ProvinciaFromFile;
import it.myevents.myevents.file.TipoEventoFromFile;
import it.myevents.myevents.file.TipologiaFromFile;
import it.myevents.myevents.file.UtenteFromFile;
import it.myevents.myevents.pojos.EventiSalvati;
import it.myevents.myevents.pojos.Evento;
import it.myevents.myevents.pojos.Utente;

@Component
public class PopolaDB implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		CittaDAO cittaDAO = new CittaDAO();
		TipologiaDAO tipologiaDAO = new TipologiaDAO();
		LocationDAO locationDAO = new LocationDAO();
		UtenteDAO utenteDAO = new UtenteDAO();
		EventoDAO eventoDAO = new EventoDAO();
		TipoEventoDAO tipoEventoDAO = new TipoEventoDAO();
		EventiSalvatiDAO eventiSalvatiDAO = new EventiSalvatiDAO();
		OccorrenzaEventoDAO occorrenzaEventoDAO = new OccorrenzaEventoDAO();

		provinciaDAO.insertAll(new ProvinciaFromFile().read());
		cittaDAO.insertAll(new CittaFromFile().read());
		utenteDAO.insertAll(new UtenteFromFile().read());
		tipologiaDAO.insertAll(new TipologiaFromFile().read());
		locationDAO.insertAll(new LocationFromFile().read());
		eventoDAO.insertAll(new EventoFromFile().read());
		occorrenzaEventoDAO.insertAll(new OccorrenzaEventoFromFile().read());
		tipoEventoDAO.insertAll(new TipoEventoFromFile().read());
		eventiSalvatiDAO.insertAll(eventiSalvatiPopulate());

		utenteDAO.insert(new Utente("admin@admin.it", "admin", "admin", "admin", cittaDAO.getByName("Roma").get()));

		System.out.println("esecuzione terminata");
	}

	public static Collection<EventiSalvati> eventiSalvatiPopulate() {
		Set<EventiSalvati> es = new HashSet<>();
		int cicli = 100;
		for (int i = 0; i < cicli; i++) {
			Evento e = new EventoDAO().getRandom().get();
			Utente u = new UtenteDAO().getRandom().get();
			es.add(new EventiSalvati(u, e));
		}
		return es;
	}
}