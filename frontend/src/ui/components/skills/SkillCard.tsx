import type {SimSkill} from "../../../types/database.ts";
import styles from "./SkillCard.module.css";
import Button from "../shared/common/Button.tsx";
import {X} from "lucide-react";
import {useState} from "react";
import ConfirmDialog from "../shared/dialogs/ConfirmDialog.tsx";

interface SkillCardProps {
    skill: SimSkill;
    onLevelChange: (simSkillId: number, level: number) => void;
    onRemoveSkill: (simSkillId: number) => void;
    isUpdating: boolean;
}

const SkillCard =
    ({skill, onLevelChange, onRemoveSkill, isUpdating}: SkillCardProps) => {
    const [isConfirmOpen, setConfirmOpen] = useState(false);

    return (
        <div className={styles.skill}>
            <span className={styles.skillName}>{skill.skill.name}</span>

            <div className={styles.actions}>
                <Button
                    variant="level"
                    onClick={() => onLevelChange(skill.simSkillId, skill.level - 1)}
                    disabled={skill.level === 1 || isUpdating}
                >
                    −
                </Button>
                <span className={styles.level}>{skill.level}</span>
                <Button
                    variant="level"
                    onClick={() => onLevelChange(skill.simSkillId, skill.level + 1)}
                    disabled={skill.level === skill.skill.levelCap || isUpdating}
                >
                    +
                </Button>

                <Button variant="secondary" className={styles.remove} onClick={() => setConfirmOpen(true)}>
                    <X size={15}/>
                </Button>

                <ConfirmDialog
                    open={isConfirmOpen}
                    onOpenChange={setConfirmOpen}
                    onAction={() => onRemoveSkill(skill.simSkillId)}
                    message={`Skill "${skill.skill.name}" and its progress will be deleted.`}
                    actionLabel="Delete"
                />
            </div>
        </div>
    )
}

export default SkillCard;