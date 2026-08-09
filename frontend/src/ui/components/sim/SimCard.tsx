import type {Sim} from "../../../types/database.ts";
import {Cake, Pencil, Skull} from "lucide-react";

interface SimCardProps {
    sim: Sim;
    onEdit: (simId: number) => void;
    onAgeUp: (simId: number) => void;
    onDeath: (simId: number) => void;
}

const SimCard = ({sim, onEdit, onAgeUp, onDeath}: SimCardProps) => {
    return (
        <div>
            <h3>{sim.name}</h3>
            <h4>{sim.familyRole.name}</h4>
            <p>{sim.lifeStage}</p>
            <p>{sim.alive ? "Alive" : "Dead"}</p>

            <button onClick={() => onEdit(sim.simId)}><Pencil/></button>
            <button onClick={() => onAgeUp(sim.simId)}><Cake/></button>
            <button onClick={() => onDeath(sim.simId)}><Skull/></button>
        </div>
    )
}

export default SimCard;