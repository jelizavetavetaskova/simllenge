import {BrowserRouter, Route, Routes} from "react-router-dom";
import RunsList from "./ui/pages/RunsList.tsx";
import RunPage from "./ui/pages/RunPage.tsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/challenges/:challengeId/runs" element={<RunsList />} />
                <Route path="/challenges/:challengeId/runs/:runId" element={<RunPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;