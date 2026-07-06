package it.uniroma3.siw.football;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import it.uniroma3.siw.football.model.Partita;
import it.uniroma3.siw.football.repository.PartitaRepository;
import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
public class FootballApplication implements CommandLineRunner {

	public FootballApplication(PartitaRepository partitaRepository, EntityManagerFactory entityManagerFactory) {
		this.partitaRepository = partitaRepository;
		this.entityManagerFactory = entityManagerFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

	private final PartitaRepository partitaRepository;

	private final EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		esCalendario();
	}

	private void esCalendario() {
		Statistics stats = entityManagerFactory
				.unwrap(SessionFactory.class)
				.getStatistics();
		stats.setStatisticsEnabled(true);
		stats.clear(); // azzera i contatori prima della misurazione
		Long torneoId = 1L;
		StopWatch watch = new StopWatch();
		watch.start("esCalendario");
		List<Partita> partite = partitaRepository.findByTournamentFetchTeamsAndReferee(torneoId);
		for (Partita p : partite) {
			p.getHomeTeam().getName();
			p.getAwayTeam().getName();
			p.getArbitro().getLastName();
		}
		watch.stop();
		System.out.println(watch.prettyPrint());
		System.out.println("Numero di query SQL eseguite: " + stats.getPrepareStatementCount());
	}

}
