import type {LifeStage} from "./database.ts";

export interface CreateRun {
    budget: number;
    stageId: number;
}

export interface CreateSim {
    name: string;
    familyRoleId: number;
    lifeStage: LifeStage;
}

export interface UpdateSim {
    name: string;
    familyRoleId: number;
    lifeStage: LifeStage;
}

export interface Register {
    username: string,
    email: string;
    password: string;
}

export interface Login {
    email: string;
    password: string;
}

export interface AddSimSkill {
    skillId: number;
}

export interface AddSimCareer {
    careerBranchId: number;
}