import type {CreateRun} from "../types/app.ts";
import type {Run} from "../types/database.ts";

export const createRun = async (run: CreateRun) => {
    const res = await fetch(`/api/challenges/1/runs`, { // TODO change to real id
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