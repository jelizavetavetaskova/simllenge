import {BrowserRouter, Route, Routes} from "react-router-dom";
import RunsList from "./ui/pages/RunsList.tsx";
import RunPage from "./ui/pages/RunPage.tsx";
import RegisterPage from "./ui/pages/RegisterPage.tsx";
import LoginPage from "./ui/pages/LoginPage.tsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/challenges" element={<h1>Challenges</h1>}/>
                <Route path="/challenges/:challengeId/runs" element={<RunsList />} />
                <Route path="/challenges/:challengeId/runs/:runId" element={<RunPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/login" element={<LoginPage />}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;