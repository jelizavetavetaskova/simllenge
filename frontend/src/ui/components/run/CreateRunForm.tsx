import {useState, type SubmitEvent, useEffect} from "react";
import {createRun} from "../../../service/runService.ts";
import type {CreateRun} from "../../../types/app.ts";
import type {Stage} from "../../../types/database.ts";
import {getAllStages} from "../../../service/stageService.ts";

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
            try {
                setStages(await getAllStages());
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            }
        }

        fetchStages();
    }, []);

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
            <form onSubmit={saveRun}>
                <label htmlFor="budget">Budget: </label>
                <input
                    id="budget"
                    type="number"
                    placeholder="5000"
                    value={budget}
                    onChange={(e) => setBudget(e.target.value)}
                />

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

                <button type="submit">Save run</button>
            </form>

            {error && <p>{error}</p>}
        </>
    )
}

export default CreateRunForm;