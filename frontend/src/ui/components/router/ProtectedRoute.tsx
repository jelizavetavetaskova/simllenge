import {useAuth} from "../../../context/auth/AuthProvider.tsx";
import {Navigate} from "react-router-dom";
import type {ReactNode} from "react";

interface ProtectedRouteProps {
    roles?: ("ADMIN"|"USER")[];
    children: ReactNode;
}

const ProtectedRoute = ({roles, children}: ProtectedRouteProps) => {
    const {user, loading} = useAuth();

    if (loading) return <p>Loading...</p>;

    if (!user) {
        return <Navigate to="/login"/>
    }

    if (roles && !roles.includes(user.authority)) {
        return <Navigate to="/challenges" />
    }

    return children;
}

export default ProtectedRoute;