import {useAuth} from "../../../../context/auth/AuthProvider.tsx";
import {Link} from "react-router-dom";
import Button from "./Button.tsx";
import styles from "./Navbar.module.css";
import {Moon, Sun, User} from "lucide-react";
import {useTheme} from "../../../../context/theme/ThemeProvider.tsx";

const Navbar = () => {
    const {user, signOut} = useAuth();

    const {darkMode, toggleTheme} = useTheme();

    return (
        <div className={styles.navbar}>
            <div className={styles.siteName}>
                SIMLLENGE
            </div>

            <div className={styles.navigation}>
                <Link to="/challenges">Challenges</Link>
            </div>

            <div className={styles.auth}>
                <Button
                    variant="secondary"
                    className={styles.theme}
                    onClick={toggleTheme}
                >
                    {darkMode ? <Sun size={28}/> : <Moon size={28}/>}
                </Button>

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