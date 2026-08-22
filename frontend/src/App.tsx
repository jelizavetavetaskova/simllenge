import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import RunsList from "./ui/pages/run/RunsList.tsx";
import RunPage from "./ui/pages/run/RunPage.tsx";
import RegisterPage from "./ui/pages/auth/RegisterPage.tsx";
import LoginPage from "./ui/pages/auth/LoginPage.tsx";
import AuthProvider from "./context/auth/AuthProvider.tsx";
import ProtectedRoute from "./ui/components/router/ProtectedRoute.tsx";
import ChallengesList from "./ui/pages/challenge/ChallengesList.tsx";
import Navbar from "./ui/components/shared/common/Navbar.tsx";
import {ThemeProvider} from "./context/theme/ThemeProvider.tsx";

const App = () => {
    return (
        <AuthProvider>
            <ThemeProvider>
                <BrowserRouter>
                    <Navbar />
                    <Routes>
                        <Route path="/" element={<Navigate to="/challenges" />}></Route>
                        <Route path="/challenges" element={<ChallengesList />} />
                        <Route path="/challenges/:challengeId/runs" element={
                            <ProtectedRoute>
                                <RunsList />
                            </ProtectedRoute>
                        } />
                        <Route path="/challenges/:challengeId/runs/:runId" element={
                            <ProtectedRoute>
                                <RunPage />
                            </ProtectedRoute>
                        } />
                        <Route path="/register" element={<RegisterPage />} />
                        <Route path="/login" element={<LoginPage />}/>
                    </Routes>
                </BrowserRouter>
            </ThemeProvider>
        </AuthProvider>
    );
}

export default App;