import api from "./api";
import type { Game } from "../types";

export async function getCalendar(tournamentId: number): Promise<Game[]> {
  const { data } = await api.get<Game[]>(`/rest/tournaments/${tournamentId}/calendar`);
  return data;
}