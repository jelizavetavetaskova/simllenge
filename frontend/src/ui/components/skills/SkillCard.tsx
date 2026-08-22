import type {SimSkill} from "../../../types/database.ts";
import styles from "./SkillCard.module.css";
import Button from "../shared/Button.tsx";
import {X} from "lucide-react";

interface SkillCardProps {
    skill: SimSkill;
    onLevelChange: (simSkillId: number, level: number) => void;
    isUpdating: boolean;
}

const SkillCard = ({skill, onLevelChange, isUpdating}: SkillCardProps) => {
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

                <Button variant="secondary" className={styles.remove}><X size={15}/></Button>
            </div>
        </div>
    )
}

export default SkillCard;