import type {AddSimSkill} from "../types/app.ts";
import type {SimSkill} from "../types/database.ts";

export const addSimSkill = async (simId: number, simSkill: AddSimSkill) => {
    const res = await fetch(`/api/sims/${simId}/skills`, {
        method: "POST",
        credentials: "include",
        body: JSON.stringify(simSkill),
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) throw Error(await res.text());

    const data: SimSkill = await res.json();
    return data;
}