import type {FamilyRole} from "../types/database.ts";

export const getAllFamilyRoles = async () => {
    const res = await fetch("/api/family-roles");

    if (!res.ok) throw Error(await res.text());

    const data: FamilyRole[] = await res.json();
    return data;
}