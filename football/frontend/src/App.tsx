import CalendarPage from "./pages/CalendarPage";

interface Props {
  tournamentId: number;
}

export default function App({ tournamentId }: Props) {
  return <CalendarPage tournamentId={tournamentId} />;
}