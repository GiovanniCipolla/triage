package it.prova.triage;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.Ruolo;
import it.prova.triage.model.StatoUtente;
import it.prova.triage.model.Utente;
import it.prova.triage.service.paziente.PazienteService;
import it.prova.triage.service.ruolo.RuoloService;
import it.prova.triage.service.utente.UtenteService;

@SpringBootApplication
public class TriageApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private PazienteService pazienteServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(TriageApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//inserimento ruoli
				if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
					ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
				}

				if (ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR) == null) {
					ruoloServiceInstance.inserisciNuovo(new Ruolo("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
				}
				
				//inserimento utenti
				if (utenteServiceInstance.findByUsername("admin") == null) {
					Utente admin = Utente.builder().username("admin").password("admin").nome("Paolo").cognome("Paoli").dateCreated(LocalDate.now()).stato(StatoUtente.CREATO).build();
					admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
					utenteServiceInstance.inserisciNuovo(admin);
					// l'inserimento avviene come created ma io voglio attivarlo
					utenteServiceInstance.changeUserAbilitation(admin.getId());
				}

				if (utenteServiceInstance.findByUsername("suboperator") == null) {
					Utente subOperator = Utente.builder().username("suboperator").password("suboperator").nome("Antonio").cognome("Verdi").dateCreated(LocalDate.now()).stato(StatoUtente.CREATO).build();
					subOperator.getRuoli()
							.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Sub Operator", Ruolo.ROLE_SUB_OPERATOR));
					utenteServiceInstance.inserisciNuovo(subOperator);
					// l'inserimento avviene come created ma io voglio attivarlo
					utenteServiceInstance.changeUserAbilitation(subOperator.getId());
				}
				
				Paziente paz1 = Paziente.builder().nome("Lionel").cognome("Messi").codiceFiscale("leomessi10").build();
				Paziente paz2 = Paziente.builder().nome("Cristiano").cognome("Ronaldo").codiceFiscale("cr7").build();
				Paziente paz3 = Paziente.builder().nome("Alvaro").cognome("Morata").codiceFiscale("amora9").build();
				
				pazienteServiceInstance.inserisciNuovo(paz1);
				pazienteServiceInstance.inserisciNuovo(paz2);
				pazienteServiceInstance.inserisciNuovo(paz3);
		
		

	}
}
