import { useEffect, useState } from "react";
import { getCalendar } from "../services/tournamentService";
import type { Game } from "../types";
import GameRow from "../components/GameRow";

interface Props {
  tournamentId: number;
}

export default function CalendarPage({ tournamentId }: Props) {
  const [games, setGames] = useState<Game[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    getCalendar(tournamentId)
      .then(setGames)
      .catch(() => setError("Errore nel caricamento del calendario."))
      .finally(() => setLoading(false));
  }, [tournamentId]);

  if (loading) return <p>Caricamento...</p>;
  if (error) return <p className="error-banner">{error}</p>;

  return (
    <>
      <h1>Calendario</h1>
      {games.length === 0 ? (
        <p>Nessuna partita in programma.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Data e ora</th>
              <th>Casa</th>
              <th>Risultato</th>
              <th>Ospite</th>
              <th>Luogo</th>
              <th>Stato</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {games.map(game => (
              <GameRow key={game.id} game={game} />
            ))}
          </tbody>
        </table>
      )}
    </>
  );
}