import {useState, type SubmitEvent} from "react";
import {createRun} from "../../service/runService.ts";
import type {CreateRun} from "../../types/app.ts";

interface CreateRunFormProps {
    challengeId?: string;
    onSuccess: () => Promise<void>;
    onClose: () => void;
}

const CreateRunForm = ({challengeId, onSuccess, onClose}: CreateRunFormProps) => {
    const [budget, setBudget] = useState("");

    const [error, setError] = useState("");

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

        const run: CreateRun = {budget: Number(budget)}

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

                <button type="submit">Save run</button>
            </form>

            {error && <p>{error}</p>}
        </>
    )
}

export default CreateRunForm;