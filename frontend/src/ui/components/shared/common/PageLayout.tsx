import styles from "./PageLayout.module.css";
import type {ReactNode} from "react";

interface PageLayoutProps {
    heading: string;
    action?: ReactNode;
    subheading?: ReactNode,
    children: ReactNode;
}

const PageLayout = ({heading, action, subheading, children}: PageLayoutProps) => {
    return (
        <div className={styles.page}>
            <div className={styles.head}>
                <div className={styles.heading}>
                    <h1>{heading}</h1>
                    {subheading &&
                        <div className={styles.subheading}>
                            {subheading}
                        </div>
                    }
                </div>

                {action}
            </div>

            {children}
        </div>
    )
}

export default PageLayout;