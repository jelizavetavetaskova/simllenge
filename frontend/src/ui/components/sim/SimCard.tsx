import type {Sim} from "../../../types/database.ts";

interface SimCardProps {
    sim: Sim;
}

const SimCard = ({sim}: SimCardProps) => {
    return (
        <div>
            <h3>{sim.name}</h3>
            <h4>{sim.familyRole.name}</h4>
            <p>{sim.lifeStage}</p>
            <p>{sim.alive ? "Alive" : "Dead"}</p>
        </div>
    )
}

export default SimCard;