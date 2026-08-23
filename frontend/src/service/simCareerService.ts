import type {Career} from "../types/database.ts";

export const getSuggestedCareers = async (simId: number) => {
    const res = await fetch(`/api/sims/${simId}/careers/suggested`, {
        credentials: "include"
    });

    if (!res.ok) throw Error(await res.text());

    const data: Career[] = await res.json();
    return data;
}