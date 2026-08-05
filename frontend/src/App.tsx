import {BrowserRouter, Route, Routes} from "react-router-dom";
import RunPage from "./ui/pages/RunPage.tsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/challenges/:challengeId/runs/new" element={<RunPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;