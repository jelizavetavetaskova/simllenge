import {useEffect, useState, type SubmitEvent} from "react";
import type {Skill} from "../../../types/database.ts";
import {getAllSkills} from "../../../service/skillService.ts";
import {addSimSkill} from "../../../service/simSkillService.ts";
import Button from "../shared/Button.tsx";
import styles from "./SkillForm.module.css";

interface SkillFormProps {
    simId: number;
    onClose: () => void;
    onSuccess: () => void;
}

const SkillForm = ({simId, onClose, onSuccess}: SkillFormProps) => {
    const [skills, setSkills] = useState<Skill[]>([]);
    const [chosenSkillIds, setChosenSkillIds] = useState<number[]>([]);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);

    const handleSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            await Promise.all(chosenSkillIds.map(id => addSimSkill(simId, {skillId: id})));
            onSuccess();
            onClose();
            setChosenSkillIds([]);
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    useEffect(() => {
        const fetchSkills = async () => {
            try {
                setSkills(await getAllSkills());
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchSkills();
    }, []);

    return (
        <div>
            <form onSubmit={handleSubmit}>
                {loading ? (
                    <p>Loading...</p>
                ): error ? (
                    <p>{error}</p>
                    ) : (
                    <div className={styles.skills}>
                        {skills.map(skill => (
                            <label key={skill.skillId} className={`${styles.skill} ${chosenSkillIds.includes(skill.skillId) ? styles.checked : ""}`}>
                                <input
                                    type="checkbox"
                                    checked={chosenSkillIds.includes(skill.skillId)}
                                    value={skill.skillId}
                                    onChange={(e) => {
                                        const id = Number(e.target.value);
                                        const isChecked = e.target.checked;

                                        if (isChecked) {
                                            setChosenSkillIds((prev) => ([...prev, id]));
                                        } else {
                                            setChosenSkillIds((prev) => prev.filter((sid) => sid != id))
                                        }
                                    }}
                                />
                                <span>{skill.name}</span>
                            </label>
                        ))}
                    </div>
                )}

                <Button variant="primary" type="submit">Save</Button>
            </form>
        </div>
    );
}

export default SkillForm;