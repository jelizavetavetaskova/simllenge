import {useEffect, useState} from "react";
import type {Run} from "../../types/database.ts";
import {Link, useParams} from "react-router-dom";
import {getChallengeRuns} from "../../service/runService.ts";
import CreateRunForm from "../components/run/CreateRunForm.tsx";
import {ArrowRight} from "lucide-react";
import styles from "./RunsList.module.css";
import Button from "../components/shared/Button.tsx";
import Modal from "../components/shared/Modal.tsx";

const RunsList = () => {
    const [runs, setRuns] = useState<Run[]>([]);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const [isModalOpen, setModalOpen] = useState(false);

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

    return (
        <div className={styles.page}>
            <div className={styles.run}>
                <h1>Runs</h1>
                <Button
                    variant="primary"
                    type="button"
                    onClick={() => setModalOpen(true)}
                >
                    + Create a run
                </Button>
            </div>

            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : runs.length === 0 ? (
                <p>No runs</p>
            ) : (
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Budget</th>
                        <th>Stage</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {runs.map(run => (
                        <tr key={run.runId}>
                            <td>#{run.runId}</td>
                            <td>${run.budget}</td>
                            <td>{run.stage.name}</td>
                            <td><Link to={`/challenges/${challengeId}/runs/${run.runId}`}><ArrowRight /></Link></td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            <Modal open={isModalOpen} onOpenChange={setModalOpen} title="Create a run">
                <CreateRunForm
                    challengeId={challengeId}
                    onSuccess={getRuns}
                    onClose={() => setModalOpen(false)}
                />
            </Modal>
        </div>
    )
}

export default RunsList;