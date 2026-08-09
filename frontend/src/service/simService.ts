import type {Sim} from "../types/database.ts";
import type {CreateSim, UpdateSim} from "../types/app.ts";

export const getSimsByRun = async (runId: string) => {
    const res = await fetch(`/api/runs/${runId}/sims`);

    if (!res.ok) throw Error(await res.text());

    const data: Sim[] = await res.json();
    return data;
}

export const createSim = async (runId: string, sim: CreateSim) => {
    const res = await fetch(`/api/runs/${runId}/sims`, {
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

export const updateSim = async (simId: number, sim: UpdateSim) => {
    const res = await fetch(`/api/sims/${simId}`, {
        method: "PUT",
        body: JSON.stringify(sim),
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) throw new Error(await res.text());

    const data: Sim = await res.json();
    return data;
}

export const markSimAsDead = async (simId: number) => {
    const res = await fetch(`/api/sims/${simId}/kill`, {
        method: "POST"
    });

    if (!res.ok) throw Error(await res.text());

    const data: Sim = await res.json();
    return data;
}