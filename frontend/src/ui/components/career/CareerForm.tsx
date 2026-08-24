import {useEffect, useState, type SubmitEvent} from "react";
import type {Career, CareerBranch} from "../../../types/database.ts";
import {addCareer, getSuggestedCareers} from "../../../service/simCareerService.ts";
import styles from "./CareerForm.module.css";
import Button from "../shared/common/Button.tsx";

interface CareerFormProps {
    simId: number;
    onClose: () => void;
    onSuccess: () => void;
}

const CareerForm = ({simId, onSuccess, onClose}: CareerFormProps) => {
    const [careers, setCareers] = useState<Career[]>([]);

    const [chosenCareer, setChosenCareer] = useState<Career|null>(null);
    const [chosenBranch, setChosenBranch] = useState<CareerBranch|null>(null);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);
    const [saving, setSaving] = useState(false);

    const handleSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            setSaving(true);

            if (!chosenBranch) {
                setError("Choose a branch!");
                return;
            }

            await addCareer(simId, {careerBranchId: chosenBranch.careerBranchId})
            onSuccess();
            onClose();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setSaving(false);
        }
    }

    useEffect(() => {
        const fetchSuggestedCareers = async () => {
            try {
                setCareers(await getSuggestedCareers(simId));
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchSuggestedCareers();
    }, [simId]);

    return (
        <div>
            <form onSubmit={handleSubmit}>
                {loading ? (
                    <p>Loading...</p>
                ) : (
                    <>
                        <div className={styles.sectionTitle}>Career</div>
                        <div className={styles.options}>
                            {careers.map(career => (
                                <label
                                    key={career.careerId}
                                    className={`
                                        ${styles.option} 
                                        ${chosenCareer?.careerId === career.careerId ? styles.checked : ""}
                                    `}
                                >
                                    <input
                                        type="radio"
                                        checked={chosenCareer?.careerId === career.careerId}
                                        onChange={() => {
                                            setChosenCareer(career);
                                            setChosenBranch(null);
                                        }}
                                    />
                                    <span>{career.name}</span>
                                </label>
                            ))}
                        </div>

                        {chosenCareer &&
                            <>
                                <div className={styles.sectionTitle}>Career branch</div>
                                <div className={styles.options}>
                                    {chosenCareer.careerBranches.map(branch => (
                                        <label
                                            key={branch.careerBranchId}
                                            className={`
                                                ${styles.option} 
                                                ${chosenBranch?.careerBranchId === branch.careerBranchId ? 
                                                    styles.checked : ""}
                                            `}>
                                            <input
                                                type="radio"
                                                checked={chosenBranch?.careerBranchId === branch.careerBranchId}
                                                onChange={() => setChosenBranch(branch)}
                                            />
                                            <span>{branch.name}</span>
                                        </label>
                                    ))}
                                </div>
                            </>
                        }

                        <Button variant="primary" disabled={!chosenBranch || saving} type="submit">
                            {saving ? "Saving..." : "Save"}
                        </Button>

                        {error && <p>{error}</p>}
                    </>
                )}
            </form>
        </div>
    )
}

export default CareerForm;