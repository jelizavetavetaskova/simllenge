import {useEffect, useState} from "react";
import type {Run} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getChallengeRuns} from "../../service/runService.ts";
import * as Dialog from "@radix-ui/react-dialog";
import CreateRunForm from "../components/CreateRunForm.tsx";
import {X} from "lucide-react";
import styles from "./RunPage.module.css"

const RunPage = () => {
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
        <div>
            <h1>Runs</h1>

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
            )}

            <button onClick={() => setModalOpen(true)}>Create run</button>

            <Dialog.Root open={isModalOpen} onOpenChange={setModalOpen}>
                <Dialog.Portal>
                    <Dialog.Overlay className={styles.overlay} />

                    <Dialog.Content className={styles.content}>
                        <Dialog.Close asChild>
                            <button><X size={15}/></button>
                        </Dialog.Close>
                        <Dialog.Title>Create run</Dialog.Title>
                        <CreateRunForm challengeId={challengeId} onSuccess={getRuns} onClose={() => setModalOpen(false)}/>
                    </Dialog.Content>

                </Dialog.Portal>
            </Dialog.Root>
        </div>
    )
}

export default RunPage;