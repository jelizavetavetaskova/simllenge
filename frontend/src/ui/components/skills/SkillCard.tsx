import type {SimSkill} from "../../../types/database.ts";
import styles from "./SkillCard.module.css";
import Button from "../shared/Button.tsx";
import {X} from "lucide-react";

interface SkillCardProps {
    skill: SimSkill;
}

const SkillCard = ({skill}: SkillCardProps) => {
    return (
        <div className={styles.skill}>
            <span className={styles.skillName}>{skill.skill.name}</span>

            <div className={styles.actions}>
                <Button variant="level">−</Button>
                <span className={styles.level}>{skill.level}</span>
                <Button variant="level">+</Button>
                <Button variant="secondary" className={styles.remove}><X size={15}/></Button>
            </div>
        </div>
    )
}

export default SkillCard;