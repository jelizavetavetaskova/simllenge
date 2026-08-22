import {useState, type SubmitEvent, type ChangeEvent, useEffect} from "react";
import type {CreateSim} from "../../../types/app.ts";
import {type FamilyRole, LIFE_STAGES, type Sim} from "../../../types/database.ts";
import {getAllFamilyRoles} from "../../../service/familyRoleService.ts";
import {createSim, updateSim} from "../../../service/simService.ts";
import styles from "./SimForm.module.css";
import Button from "../shared/common/Button.tsx";
import InputField from "../shared/fields/InputField.tsx";
import SelectField from "../shared/fields/SelectField.tsx";

interface CreateSimFormProps {
    sim?: Sim
    runId?: string;
    onSuccess: () => Promise<void>;
    onClose: () => void;
}

const SimForm = ({sim, runId, onSuccess, onClose}: CreateSimFormProps) => {
    const [formData, setFormData] = useState<CreateSim>({
        name: sim?.name ?? "",
        familyRoleId: sim?.familyRole.familyRoleId ?? 3, // Member
        lifeStage: sim?.lifeStage ?? "NEWBORN"
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

        if (!runId) {
            setError("Run id is required");
            return;
        }

        try {
            if (sim) {
                await updateSim(sim.simId, formData);
            } else {
                await createSim(runId, formData);
            }
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
            <form onSubmit={saveSim} className={styles.form}>
                <div className={styles.fields}>
                    <InputField
                        id="name"
                        name="name"
                        label="Name: "
                        inputType="text"
                        value={formData.name}
                        onChange={handleChange}
                    />

                    <SelectField
                        id="lifeStage"
                        name="lifeStage"
                        label="Life stage: "
                        value={formData.lifeStage}
                        onChange={handleChange}
                    >
                        {LIFE_STAGES.map(stage => (
                            <option value={stage} key={stage}>{stage}</option>
                        ))}
                    </SelectField>

                    <SelectField
                        id="familyRole"
                        name="familyRoleId"
                        label="Family role: "
                        value={formData.familyRoleId}
                        onChange={handleChange}
                    >
                        {familyRoles.map(role => (
                            <option value={role.familyRoleId} key={role.familyRoleId}>{role.name}</option>
                        ))}
                    </SelectField>
                </div>

                <div className={styles.buttons}>
                    <Button variant="secondary" onClick={onClose} type="button">Cancel</Button>
                    <Button variant="primary" type="submit">Save sim</Button>
                </div>
            </form>

            {error && <p>{error}</p>}
        </>
    )
}

export default SimForm;