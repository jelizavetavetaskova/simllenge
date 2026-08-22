import {useEffect, useState} from "react";
import type {Run} from "../../../types/database.ts";
import {Link, useParams} from "react-router-dom";
import {getChallengeRuns} from "../../../service/runService.ts";
import CreateRunForm from "../../components/run/CreateRunForm.tsx";
import {ArrowRight} from "lucide-react";
import Button from "../../components/shared/common/Button.tsx";
import Modal from "../../components/shared/dialogs/Modal.tsx";
import PageLayout from "../../components/shared/common/PageLayout.tsx";
import styles from "./RunsList.module.css";

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
        <PageLayout
            heading="My runs"
            action={
                <Button
                    variant="add"
                    type="button"
                    onClick={() => setModalOpen(true)}
                >
                    + Create a run
                </Button>
            }
        >

            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : runs.length === 0 ? (
                <p className={styles.noRuns}>No runs</p>
            ) : (
                <div className={styles.runs}>
                    <div className={styles.header}>
                        <div>ID</div>
                        <div>Budget</div>
                        <div>Stage</div>
                        <div></div>
                    </div>

                    {runs.map(run => (
                        <div key={run.runId} className={styles.content}>
                            <div className={styles.runId}>#{run.runId}</div>
                            <div className={styles.budget}><span>${run.budget}</span></div>
                            <div className={styles.stage}><span>Stage {run.stage.stageOrder} - {run.stage.name}</span></div>
                            <div className={styles.link}><Link to={`/challenges/${challengeId}/runs/${run.runId}`}><ArrowRight /></Link></div>
                        </div>
                    ))}
                </div>
            )}

            <Modal open={isModalOpen} onOpenChange={setModalOpen} title="Create a run">
                <CreateRunForm
                    challengeId={challengeId}
                    onSuccess={getRuns}
                    onClose={() => setModalOpen(false)}
                />
            </Modal>
        </PageLayout>
    )
}

export default RunsList;