import {useEffect, useState} from "react";
import type {Run} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getChallengeRuns} from "../../service/runService.ts";

const RunPage = () => {
    const [runs, setRuns] = useState<Run[]>([]);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const {challengeId} = useParams();

    const getRuns = async () => {
        setError("");

        if (!challengeId) {
            setError("Challenge id is required");
            return;
        }

        try {
            setLoading(true);
            setRuns(await getChallengeRuns(challengeId));
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        getRuns();
    }, [challengeId]);

    const renderContent = () => {
        if (loading) return <p>Loading...</p>;
        if (error) return <p>{error}</p>;
        if (runs.length === 0) return <p>No runs</p>;
        return (
            <table>
                <thead>
                <tr>
                    <th>Run ID</th>
                    <th>Budget</th>
                </tr>
                </thead>
                <tbody>
                {runs.map(run => (
                    <tr key={run.runId}>
                        <td>{run.runId}</td>
                        <td>{run.budget}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        )

    }

    return (
        <div>
            <h1>Runs</h1>
            {renderContent()}
        </div>
    )
}

export default RunPage;