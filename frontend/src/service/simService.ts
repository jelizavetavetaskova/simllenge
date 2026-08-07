import type {Sim} from "../types/database.ts";
import type {CreateSim} from "../types/app.ts";

export const getSimsByRun = async (challengeId: string, runId: string) => {
    const res = await fetch(`/api/challenges/${challengeId}/runs/${runId}/sims`);

    if (!res.ok) throw Error(await res.text());

    const data: Sim[] = await res.json();
    return data;
}

export const createSim = async (challengeId: string, runId: string, sim: CreateSim) => {
    const res = await fetch(`/api/challenges/${challengeId}/runs/${runId}/sims`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(sim)
    });

    if (!res.ok) throw Error(await res.text());

    const data: Sim = await res.json();
    return data;
}