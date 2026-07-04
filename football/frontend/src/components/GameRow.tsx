import type { Game } from "../types";

interface Props {
    game: Game;
}

function formatDateTime(iso: string): string {
    const d = new Date(iso);
    const pad = (n: number) => String(n).padStart(2, "0");
    return `${pad(d.getDate())}/${pad(d.getMonth() + 1)}/${d.getFullYear()} ${pad(d.getHours())}:${pad(d.getMinutes())}`;
}

export default function GameRow({ game }: Props) {
    return (
        <tr>
            <td>{formatDateTime(game.dateTime)}</td>
            <td>
                <a href={`/teams/${game.homeTeamId}`}>{game.homeTeamName}</a>
            </td>
            <td>
                {game.status === "SCHEDULED" ? "-" : `${game.goalsHome} - ${game.goalsAway}`}
            </td>
            <td>
                <a href={`/teams/${game.awayTeamId}`}>{game.awayTeamName}</a>
            </td>
            <td>{game.place}</td>
            <td>{game.status}</td>
            <td>
                <a href={`/games/${game.id}`}>Dettagli</a>
            </td>
        </tr>
    );
}