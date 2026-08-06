import {useEffect, useState} from "react";
import type {Run} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getRunById} from "../../service/runService.ts";

const RunPage = () => {
    const [run, setRun] = useState<Run|null>(null);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const {challengeId, runId} = useParams();

    useEffect(() => {
        const fetchRun = async () => {
            if (!runId) {
                setError("Run id is required");
                return;
            }
            if (!challengeId) {
                setError("Challenge id is required");
                return;
            }

            try {
                setLoading(true);
                setRun(await getRunById(challengeId, runId));
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchRun();
    }, [challengeId, runId]);

    return (
        <div>
            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : run && (
                <>
                    <h1>Run: {run.stage.name}, {run.budget}</h1>
                </>
            )
            }
        </div>
    )
}

export default RunPage;