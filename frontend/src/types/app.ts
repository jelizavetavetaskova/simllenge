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