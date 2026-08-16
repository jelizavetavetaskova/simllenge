import {useAuth} from "../../../auth/AuthProvider.tsx";
import {Link} from "react-router-dom";
import Button from "./Button.tsx";
import styles from "./Navbar.module.css";
import {User} from "lucide-react";

const Navbar = () => {
    const {user, signOut} = useAuth();

    return (
        <div className={styles.navbar}>
            <div className={styles.siteName}>
                SIMLLENGE
            </div>

            <div className={styles.navigation}>
                <Link to="/challenges">Challenges</Link>
            </div>

            <div className={styles.auth}>
                {user ? (
                    <>
                        <User/>
                        <p>{user.username}</p>
                        <Button variant="secondary" onClick={signOut}>Log out</Button>
                    </>
                ) : (
                    <>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Create an account</Link>
                    </>
                )}
            </div>
        </div>
    );
}

export default Navbar;