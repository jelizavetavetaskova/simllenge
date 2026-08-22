import type {Sim} from "../../../types/database.ts";
import {Cake, Pencil, Skull, Users} from "lucide-react";
import styles from "./SimCard.module.css";
import Button from "../shared/Button.tsx";
import Modal from "../shared/Modal.tsx";
import {useState} from "react";
import SkillForm from "../skills/SkillForm.tsx";
import SkillCard from "../skills/SkillCard.tsx";
import {updateSimSkillLevel} from "../../../service/simSkillService.ts";

interface SimCardProps {
    sim: Sim;
    onEdit?: (sim: Sim) => void;
    onAgeUp?: (simId: number) => void;
    onDeath?: (simId: number) => void;
    onSkillChanged: () => void;
}

const SimCard = ({sim, onEdit, onAgeUp, onDeath, onSkillChanged}: SimCardProps) => {
    const [addSkillModalOpen, setAddSkillModalOpen] = useState(false);
    const [error, setError] = useState("");
    const [updatingSkillId, setUpdatingSkillId] = useState<number|null>(null);

    const updateSkillLevel = async (skillId: number, level: number) => {
        try {
            setError("");
            setUpdatingSkillId(skillId);
            await updateSimSkillLevel(sim.simId, skillId, level);
            onSkillChanged();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setUpdatingSkillId(null);
        }
    }

    return (
        <div className={styles.card}>
            <div className={styles.data}>
                <span className={styles.name}>{sim.name}</span>
                <span className={styles.age}>{sim.lifeStage}</span>
            </div>

            <span className={styles.role}><Users /> {sim.familyRole.name}</span>

            {sim.alive &&
                <>
                    <div className={styles.skills}>
                        {sim.skills.map(skill => (
                            <SkillCard
                                skill={skill}
                                onLevelChange={updateSkillLevel}
                                key={skill.simSkillId}
                                isUpdating={updatingSkillId === skill.simSkillId}
                            />
                        ))}

                        <Button variant="add_secondary" className={styles.addSkill} onClick={() => setAddSkillModalOpen(true)}>
                            + skill
                        </Button>
                    </div>

                    <div className={styles.actions}>
                        <button onClick={() => onEdit?.(sim)} className={styles.edit}><Pencil/></button>
                        <button onClick={() => onAgeUp?.(sim.simId)} disabled={sim.lifeStage === "ELDER"} className={styles.age_up}><Cake/></button>
                        <button onClick={() => onDeath?.(sim.simId)} className={styles.death}><Skull/></button>
                    </div>
                </>
            }

            <Modal open={addSkillModalOpen} onOpenChange={setAddSkillModalOpen} title={`Add skills: ${sim.name}`}>
                <SkillForm simId={sim.simId} onClose={() => setAddSkillModalOpen(false)} onSuccess={onSkillChanged}/>
            </Modal>

            {error && <p>{error}</p>}
        </div>
    )
}

export default SimCard;