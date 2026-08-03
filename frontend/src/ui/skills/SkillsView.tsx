import {useEffect, useState} from "react";
import type {Skill} from "../../types/database.ts";
import {getAllSkills} from "../../service/skillService.ts";

const SkillsView = () => {
    const [skills, setSkills] = useState<Skill[]>([]);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const fetchSkills = async () => {
        try {
            setLoading(true);
            const data = await getAllSkills();
            setSkills(data);
        } catch (e: unknown) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        fetchSkills();
    }, []);

    return (
        <div>
            {loading ?
                <p>Loading...</p> :
                <>
                    {error ?
                        <p>{error}</p> :
                        <ul>
                            {skills.map(skill => (
                                <li key={skill.skillId}>{skill.name}</li>
                            ))}
                        </ul>
                    }
                </>
            }
        </div>
    )
}

export default SkillsView;