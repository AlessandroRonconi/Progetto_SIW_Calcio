export interface Game {
    id: number;
    dateTime: string; // ISO 8601, es. "2026-07-04T20:45:00"
    homeTeamId: number;
    homeTeamName: string;
    awayTeamId: number;
    awayTeamName: string;
    goalsHome: number | null;
    goalsAway: number | null;
    place: string;
    status: "SCHEDULED" | "LIVE" | "PLAYED" | "CANCELLED"; // adatta ai tuoi valori reali dell'enum
}