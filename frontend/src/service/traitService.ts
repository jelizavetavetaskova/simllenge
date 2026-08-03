import type {Trait} from "../types/database.ts";

export const getAllTraits = async () => {
    const res = await fetch("/api/traits");

    if (!res.ok) throw Error(await res.text());

    const data: Trait[] = await res.json();
    return data;
}