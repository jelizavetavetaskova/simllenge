import {useAuth} from "../../../auth/AuthProvider.tsx";
import {Link} from "react-router-dom";
import Button from "./Button.tsx";
import styles from "./Navbar.module.css";
import {Moon, User} from "lucide-react";

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
                <Moon size={28}/>

                {user ? (
                    <>
                        <div className={styles.user}>
                            <User className={styles.userIcon} size={24}/>
                            <p className={styles.username}>{user.username}</p>
                        </div>
                        <Button variant="secondary" onClick={signOut}>Log out</Button>
                    </>
                ) : (
                    <>
                        <Link to="/register" className={styles.register}>Create an account</Link>
                        <Link to="/login" className={styles.login}>Log in</Link>
                    </>
                )}
            </div>
        </div>
    );
}

export default Navbar;