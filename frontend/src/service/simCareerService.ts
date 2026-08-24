import type {Career, SimCareer} from "../types/database.ts";
import type {AddSimCareer} from "../types/app.ts";

export const getSuggestedCareers = async (simId: number) => {
    const res = await fetch(`/api/sims/${simId}/careers/suggested`, {
        credentials: "include"
    });

    if (!res.ok) throw Error(await res.text());

    const data: Career[] = await res.json();
    return data;
}

export const addCareer = async (simId: number, simCareer: AddSimCareer) => {
    const res = await fetch(`/api/sims/${simId}/careers`, {
        method: "POST",
        credentials: "include",
        body: JSON.stringify(simCareer),
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (!res.ok) throw Error(await res.text());

    const data: SimCareer = await res.json();
    return data;
}