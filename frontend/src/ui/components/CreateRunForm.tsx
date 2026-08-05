import {useState, type SubmitEvent} from "react";
import {useParams} from "react-router-dom";
import {createRun} from "../../service/runService.ts";
import type {CreateRun} from "../../types/app.ts";

const CreateRunForm = () => {
    const [budget, setBudget] = useState("");

    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const {challengeId} = useParams();

    const saveRun = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");
        setSuccess("");

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
            setBudget("");
            setSuccess("Run created successfully");
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    return (
        <>
            <form onSubmit={saveRun}>
                <label htmlFor="budget">Budget: </label>
                <input
                    type="number"
                    placeholder="5000"
                    value={budget}
                    onChange={(e) => setBudget(e.target.value)}
                />

                <button type="submit">Save run</button>
            </form>

            {error && <p>{error}</p>}
            {success && <p>{success}</p>}
        </>
    )
}

export default CreateRunForm;