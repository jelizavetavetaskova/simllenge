import type {Stage} from "../types/database.ts";

export const getChallengeStages = async (challengeId: string) => {
    const res = await fetch(`/api/challenges/${challengeId}/stages`);

    if (!res.ok) throw Error(await res.text());

    const data: Stage[] = await res.json();
    return data;
}