export type Skill = {
    skillId: number;
    name: string;
    levelCap: number;
}

export type Stage = {
    stageId: number;
    name: string;
    stageOrder: number;
    taxApplies: boolean;
}

export type Trait = {
    traitId: number;
    name: string;
}

export type FamilyRole = {
    familyRoleId: number;
    name: string;
}

export type Career = {
    careerId: number;
    name: string;
    careerType: "FULL_TIME"|"PART_TIME"|"FREELANCE";
    careerBranches: CareerBranch[];
}

export type CareerBranch = {
    careerBranchId: number;
    name: string;
}

export type Run = {
    runId: number;
    budget: number;
}