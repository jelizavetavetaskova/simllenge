import type {Challenge} from "../types/database.ts";

export const getAllChallenges = async () => {
    const res = await fetch("/api/challenges");

    if (!res.ok) throw Error(await res.text());

    const data: Challenge[] = await res.json();
    return data;
}