import {useState, type SubmitEvent, type ChangeEvent, useEffect} from "react";
import type {CreateSim} from "../../../types/app.ts";
import {type FamilyRole, LIFE_STAGES} from "../../../types/database.ts";
import {getAllFamilyRoles} from "../../../service/familyRoleService.ts";
import {createSim} from "../../../service/simService.ts";

interface CreateSimFormProps {
    challengeId?: string;
    runId?: string;
    onSuccess: () => Promise<void>;
    onClose: () => void;
}

const CreateSimForm = ({challengeId, runId, onSuccess, onClose}: CreateSimFormProps) => {
    const [formData, setFormData] = useState<CreateSim>({
        name: "",
        familyRoleId: 3,
        lifeStage: "NEWBORN"
    });

    const [familyRoles, setFamilyRoles] = useState<FamilyRole[]>([]);

    const [error, setError] = useState("");

    useEffect(() => {
        const fetchFamilyRoles = async () => {
            try {
                setFamilyRoles(await getAllFamilyRoles());
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            }
        }

        fetchFamilyRoles();
    }, []);

    const saveSim = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");

        if (!challengeId) {
            setError("Challenge id is required");
            return;
        }
        if (!runId) {
            setError("Run id is required");
            return;
        }

        try {
            await createSim(challengeId, runId, formData);
            await onSuccess();

            setFormData({
                name: "",
                familyRoleId: 3,
                lifeStage: "NEWBORN"
            });

            onClose();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    const handleChange = (e: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLSelectElement>) => {
        setFormData((prev) => ({
            ...prev,
            [e.target.name]: (e.target.name === "familyRoleId") ? Number(e.target.value) : e.target.value
        }))
    }

    return (
        <>
            <form onSubmit={saveSim}>
                <label htmlFor="name">Name: </label>
                <input type="text" id="name" name="name" value={formData.name} onChange={handleChange}/>

                <label htmlFor="lifeStage">Life stage: </label>
                <select name="lifeStage" id="lifeStage" value={formData.lifeStage} onChange={handleChange}>
                    {LIFE_STAGES.map(stage => (
                        <option value={stage} key={stage}>{stage}</option>
                    ))}
                </select>

                <label htmlFor="familyRole">Family role:</label>
                <select name="familyRoleId" id="familyRole" value={formData.familyRoleId} onChange={handleChange}>
                    {familyRoles.map(role => (
                        <option value={role.familyRoleId} key={role.familyRoleId}>{role.name}</option>
                    ))}
                </select>

                <button type="submit">Save sim</button>
            </form>

            {error && <p>{error}</p>}
        </>
    )
}

export default CreateSimForm;