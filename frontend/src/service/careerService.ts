import type {Career} from "../types/database.ts";

export const getAllCareers = async () => {
    const res = await fetch("/api/careers");

    if (!res.ok) throw Error(await res.text());

    const data: Career[] = await res.json();
    return data;
}