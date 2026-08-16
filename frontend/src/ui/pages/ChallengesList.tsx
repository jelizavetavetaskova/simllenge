import {useEffect, useState} from "react";
import type {Challenge} from "../../types/database.ts";
import {getAllChallenges} from "../../service/challengeService.ts";
import {Link} from "react-router-dom";
import styles from "./ChallengesList.module.css";
import PageLayout from "../components/shared/PageLayout.tsx";

const ChallengesList = () => {
    const [challenges, setChallenges] = useState<Challenge[]>([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchChallenges = async () => {
            try {
                setLoading(true);
                setChallenges(await getAllChallenges());
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchChallenges();
    }, []);

    return (
        <PageLayout heading="Challenges">
            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : challenges.length === 0 ? (
                <p>No challenges</p>
            ) : (
                <div className={styles.challengeList}>
                    {challenges.map(challenge => (
                        <div key={challenge.challengeId} className={styles.challenge}><Link to={`/challenges/${challenge.challengeId}/runs`}>{challenge.title}</Link></div>
                    ))}
                </div>
            )}
        </PageLayout>
    )
}

export default ChallengesList;