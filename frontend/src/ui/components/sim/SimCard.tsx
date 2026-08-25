import type {Sim} from "../../../types/database.ts";
import {Cake, Pencil, Skull, Users} from "lucide-react";
import styles from "./SimCard.module.css";
import Button from "../shared/common/Button.tsx";
import Modal from "../shared/dialogs/Modal.tsx";
import {useState} from "react";
import SkillForm from "../skills/SkillForm.tsx";
import SkillCard from "../skills/SkillCard.tsx";
import {removeSimSkill, updateSimSkillLevel} from "../../../service/simSkillService.ts";
import SimCareerForm from "../career/SimCareerForm.tsx";
import SimCareerCard from "../career/SimCareerCard.tsx";

interface SimCardProps {
    sim: Sim;
    onEdit?: (sim: Sim) => void;
    onAgeUp?: (simId: number) => void;
    onDeath?: (simId: number) => void;
    onDataChanged: () => void;
}

const SimCard = ({sim, onEdit, onAgeUp, onDeath, onDataChanged}: SimCardProps) => {
    const [updatingSkillId, setUpdatingSkillId] = useState<number|null>(null);
    const [isCareerUpdating, setCareerUpdating] = useState(false);

    const [addSkillModalOpen, setAddSkillModalOpen] = useState(false);
    const [addCareerModalOpen, setAddCareerModalOpen] = useState(false);

    const [error, setError] = useState("");

    const updateSkillLevel = async (skillId: number, level: number) => {
        try {
            setError("");
            setUpdatingSkillId(skillId);
            await updateSimSkillLevel(sim.simId, skillId, level);
            onDataChanged();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setUpdatingSkillId(null);
        }
    }

    const removeSkill = async (simSkillId: number) => {
        try {
            setError("");
            await removeSimSkill(sim.simId, simSkillId);
            onDataChanged();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    const updateCareerLevel = async (simCareerId: number, level: number) => {

    }

    const removeCareer = (simCareerId: number) => {

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
                    <div className={styles.career}>
                        {sim.careers.length > 0 ?
                            <SimCareerCard
                                career={sim.careers[0]}
                                isUpdating={isCareerUpdating}
                                onLevelChange={updateCareerLevel}
                                onRemoveCareer={removeCareer}
                            />
                            :
                            <div className={styles.addCareer}>
                                <Button variant="add_secondary" onClick={() => setAddCareerModalOpen(true)}>
                                    + Career
                                </Button>
                            </div>
                        }
                    </div>

                    <div className={styles.skills}>
                        {sim.skills.map(skill => (
                            <SkillCard
                                skill={skill}
                                onLevelChange={updateSkillLevel}
                                onRemoveSkill={removeSkill}
                                key={skill.simSkillId}
                                isUpdating={updatingSkillId === skill.simSkillId}
                            />
                        ))}

                        <Button
                            variant="add_secondary"
                            className={styles.addSkill}
                            onClick={() => setAddSkillModalOpen(true)}
                        >
                            + skill
                        </Button>
                    </div>

                    <div className={styles.actions}>
                        <button onClick={() => onEdit?.(sim)} className={styles.edit}><Pencil/></button>
                        <button
                            onClick={() => onAgeUp?.(sim.simId)}
                            disabled={sim.lifeStage === "ELDER"}
                            className={styles.age_up}
                        >
                            <Cake/>
                        </button>
                        <button onClick={() => onDeath?.(sim.simId)} className={styles.death}><Skull/></button>
                    </div>
                </>
            }

            <Modal open={addSkillModalOpen} onOpenChange={setAddSkillModalOpen} title={`Add skills: ${sim.name}`}>
                <SkillForm
                    simId={sim.simId}
                    onClose={() => setAddSkillModalOpen(false)}
                    onSuccess={onDataChanged}
                />
            </Modal>

            <Modal open={addCareerModalOpen} onOpenChange={setAddCareerModalOpen} title={`Add career: ${sim.name}`}>
                <SimCareerForm
                    simId={sim.simId}
                    onClose={() => setAddCareerModalOpen(false)}
                    onSuccess={onDataChanged}
                />
            </Modal>

            {error && <p>{error}</p>}
        </div>
    )
}

export default SimCard;