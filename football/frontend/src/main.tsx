import { StrictMode } from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

const rootElement = document.getElementById("root-calendar-page");
if (rootElement) {
  const tournamentId = Number(rootElement.dataset.tournamentId);
  const root = ReactDOM.createRoot(rootElement);
  root.render(
    <StrictMode>
      <App tournamentId={tournamentId} />
    </StrictMode>
  );
}