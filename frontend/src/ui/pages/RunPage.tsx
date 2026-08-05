import {useState, type SubmitEvent} from "react";
import type {CreateRun} from "../../types/app.ts";
import {createRun} from "../../service/runService.ts";

const RunPage = () => {
    const [budget, setBudget] = useState("");

    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const saveRun = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");
        setSuccess("");

        if (!budget) {
            setError("Budget is required");
            return;
        }

        const run: CreateRun = {budget: Number(budget)}

        try {
            await createRun(run);
            setBudget("");
            setSuccess("Run created successfully");
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    return (
        <div>
            <h1>Create a run</h1>

            <form onSubmit={saveRun}>
                <label htmlFor="budget">Budget: </label>
                <input
                    type="text"
                    placeholder="5000"
                    value={budget}
                    onChange={(e) => setBudget(e.target.value)}
                />

                <button type="submit">Save run</button>
            </form>

            {error && <p>{error}</p>}
            {success && <p>{success}</p>}
        </div>
    )
}

export default RunPage;