import type {Skill} from "../types/database.ts";

export const getAllSkills = async () => {
    const res = await fetch("/api/skills");
    if (!res.ok) throw Error(await res.text());

    const data: Skill[] = await res.json();
    return data;
}