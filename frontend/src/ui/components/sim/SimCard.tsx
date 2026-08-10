import type {Sim} from "../../../types/database.ts";
import {Cake, Clock, Pencil, PersonStanding, Skull} from "lucide-react";
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
            <h3 className={styles.name}>{sim.name}</h3>
            <h4 className={styles.role}><PersonStanding/>{sim.familyRole.name}</h4>
            <p className={styles.age}><Clock/>{sim.lifeStage}</p>

            {sim.alive &&
                <div className={styles.actions}>
                    <button onClick={() => onEdit?.(sim)}><Pencil/></button>
                    <button onClick={() => onAgeUp?.(sim.simId)} disabled={sim.lifeStage === "ELDER"}><Cake/></button>
                    <button onClick={() => onDeath?.(sim.simId)} className={styles.death}><Skull/></button>
                </div>
            }
        </div>
    )
}

export default SimCard;