package it.uniroma3.siw.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballApplication {
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

	// public FootballApplication(PartitaRepository partitaRepository,
	// EntityManagerFactory entityManagerFactory) {
	// this.partitaRepository = partitaRepository;
	// this.entityManagerFactory = entityManagerFactory;
	// }

	// private final PartitaRepository partitaRepository;

	// private final EntityManagerFactory entityManagerFactory;

	// @Override
	// @Transactional
	// public void run(String... args) throws Exception {
	// esCalendario();
	// }

	// private void esCalendario() {
	// Statistics stats = entityManagerFactory
	// .unwrap(SessionFactory.class)
	// .getStatistics();
	// stats.setStatisticsEnabled(true);
	// stats.clear(); // azzera i contatori prima della misurazione
	// Long torneoId = 1L;
	// StopWatch watch = new StopWatch();
	// watch.start("esCalendario");
	// List<Partita> partite =
	// partitaRepository.findByTournamentFetchTeamsAndReferee(torneoId);
	// for (Partita p : partite) {
	// p.getHomeTeam().getName();
	// p.getAwayTeam().getName();
	// p.getArbitro().getLastName();
	// }
	// watch.stop();
	// System.out.println(watch.prettyPrint());
	// System.out.println("Numero di query SQL eseguite: " +
	// stats.getPrepareStatementCount());
	// }

	// Studio delle strategie di fetch del caso d'uso "visualizzazione del calendario delle partite"
	// 1 (default, ovvero partite caricate lazy e hometeam, awayteam, arbitro e
	// torneo caricati eager): 0.1806521 secondi, 11 query
	// 2 (tutto caricato eager): 0.1556785 secondi, 5 query
	// 3 (partite eager, hometeam, awayteam, arbitro e torneo lazy): 0.1701247
	// secondi, 10 query
	// 4 (tutto caricato lazy): 0.1697337 secondi, 10 query
	// 5 (1 con join fetch): 0.1165135 secondi, 1 query
	// 6 (2 con join fetch): 0.1343907 secondi, 2 query
	// 7 (3 con join fetch): 0.1182286 secondi, 2 query
	// 8 (4 con join fetch): 0.1181529 secondi, 1 query

}
