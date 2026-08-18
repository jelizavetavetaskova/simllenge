import type {Sim} from "../../../types/database.ts";
import {Cake, Pencil, Skull, Users} from "lucide-react";
import styles from "./SimCard.module.css";

interface SimCardProps {
    sim: Sim;
    onEdit?: (sim: Sim) => void;
    onAgeUp?: (simId: number) => void;
    onDeath?: (simId: number) => void;
}

const SimCard = ({sim, onEdit, onAgeUp, onDeath}: SimCardProps) => {
    return (
        <div className={styles.card}>
            <div className={styles.data}>
                <span className={styles.name}>{sim.name}</span>
                <span className={styles.age}>{sim.lifeStage}</span>
            </div>

            <span className={styles.role}><Users /> {sim.familyRole.name}</span>

            {sim.alive &&
                <div className={styles.actions}>
                    <button onClick={() => onEdit?.(sim)} className={styles.edit}><Pencil/></button>
                    <button onClick={() => onAgeUp?.(sim.simId)} disabled={sim.lifeStage === "ELDER"} className={styles.age_up}><Cake/></button>
                    <button onClick={() => onDeath?.(sim.simId)} className={styles.death}><Skull/></button>
                </div>
            }
        </div>
    )
}

export default SimCard;