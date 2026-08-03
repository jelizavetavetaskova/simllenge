import type {Stage} from "../types/database.ts";

export const getAllStages = async () => {
    const res = await fetch("/api/stages");

    if (!res.ok) throw Error(await res.text());

    const data: Stage[] = await res.json();
    return data;
}