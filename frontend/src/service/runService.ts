import type {CreateRun} from "../types/app.ts";
import type {Run} from "../types/database.ts";

export const createRun = async (challengeId: string, run: CreateRun) => {
    const res = await fetch(`/api/challenges/${challengeId}/runs`, {
        method: "POST",
        body: JSON.stringify(run),
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) throw Error(await res.text());

    const data: Run = await res.json();
    return data;
}