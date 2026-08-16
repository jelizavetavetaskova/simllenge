import {useAuth} from "../../../auth/AuthProvider.tsx";
import {Link} from "react-router-dom";
import Button from "./Button.tsx";

const Navbar = () => {
    const {loading, user, signOut} = useAuth();

    return (
        <div>
            {loading ? (
                <p>Loading...</p>
            ) : (
                <div>
                    <Link to="/challenges">Challenges</Link>

                    {user ? (
                        <Button variant="secondary" onClick={signOut}>Log out</Button>
                    ) : (
                        <>
                            <Link to="/login">Login</Link>
                            <Link to="/register">Create an account</Link>
                        </>
                    )}
                </div>
            )

            }
        </div>
    )
}

export default Navbar;