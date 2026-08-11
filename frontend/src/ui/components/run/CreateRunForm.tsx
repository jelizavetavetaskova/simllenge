import {useState, type SubmitEvent, useEffect} from "react";
import {createRun} from "../../../service/runService.ts";
import type {CreateRun} from "../../../types/app.ts";
import type {Stage} from "../../../types/database.ts";
import {getChallengeStages} from "../../../service/stageService.ts";
import styles from "./CreateRunForm.module.css";
import Button from "../shared/Button.tsx";

interface CreateRunFormProps {
    challengeId?: string;
    onSuccess: () => Promise<void>;
    onClose: () => void;
}

const CreateRunForm = ({challengeId, onSuccess, onClose}: CreateRunFormProps) => {
    const [budget, setBudget] = useState("");
    const [stages, setStages] = useState<Stage[]>([]);
    const [selectedStage, setSelectedStage] = useState(1);

    const [error, setError] = useState("");

    useEffect(() => {
        const fetchStages = async () => {
            if (!challengeId) {
                setError("Challenge id is required");
                return;
            }

            try {
                setStages(await getChallengeStages(challengeId));
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            }
        }

        fetchStages();
    }, [challengeId]);

    const saveRun = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");

        if (!budget) {
            setError("Budget is required");
            return;
        }
        if (!challengeId) {
            setError("Challenge id is required");
            return;
        }

        const run: CreateRun = {
            budget: Number(budget),
            stageId: selectedStage
        }

        try {
            await createRun(challengeId, run);
            await onSuccess();

            setBudget("");
            onClose();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    return (
        <>
            <form onSubmit={saveRun} className={styles.form}>
                <div className={styles.field}>
                    <label htmlFor="budget">Budget: </label>
                    <input
                        id="budget"
                        type="number"
                        placeholder="5000"
                        value={budget}
                        onChange={(e) => setBudget(e.target.value)}
                    />
                </div>

                <div className={styles.field}>
                    <label htmlFor="stage">Stage: </label>
                    <select
                        name="stage"
                        id="stage"
                        value={selectedStage}
                        onChange={(e) => setSelectedStage(Number(e.target.value))}
                    >
                        {stages.map(stage => (
                            <option value={stage.stageId} key={stage.stageId}>{stage.name}</option>
                        ))}
                    </select>
                </div>

                <div className={styles.buttons}>
                    <Button variant="secondary" onClick={onClose} type="button">Cancel</Button>
                    <Button variant="primary" type="submit">Save run</Button>
                </div>
            </form>

            {error && <p>{error}</p>}
        </>
    )
}

export default CreateRunForm;