import {createContext, type ReactNode, useContext, useEffect, useState} from "react";

interface ThemeContextType {
    toggleTheme: () => void;
    darkMode: boolean;
}

const ThemeContext = createContext<ThemeContextType|undefined>(undefined);

export const useTheme = () => {
    const context = useContext(ThemeContext);

    if (!context) throw Error("useTheme must be used within ThemeProvider");

    return context;
}

export const ThemeProvider = ({children}: {children: ReactNode}) => {
    const [darkMode, setDarkMode] = useState(() => {
        const saved = localStorage.getItem("theme");
        return saved ? saved === "dark" : false;
    });

    const toggleTheme = () => {
        setDarkMode(mode => !mode);
    }

    useEffect(() => {
        document.documentElement.setAttribute("data-theme", darkMode ? "dark" : "light");
        localStorage.setItem("theme", darkMode ? "dark" : "light");
    }, [darkMode]);

    return (
        <ThemeContext.Provider value={{toggleTheme, darkMode}}>
            {children}
        </ThemeContext.Provider>
    );
}