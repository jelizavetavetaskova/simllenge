import type {SimCareer} from "../../../types/database.ts";
import styles from "./SimCareerCard.module.css";
import {BriefcaseBusiness, X} from "lucide-react";
import dayjs from "dayjs";
import Button from "../shared/common/Button.tsx";
import {useState} from "react";
import ConfirmDialog from "../shared/dialogs/ConfirmDialog.tsx";

interface SimCareerCardProps {
    career: SimCareer;
    onLevelChange: (simCareerId: number, level: number) => void;
    onRemoveCareer: (simCareerId: number) => void;
    isUpdating: boolean
}

const SimCareerCard =
    ({career, onLevelChange, onRemoveCareer, isUpdating}: SimCareerCardProps) => {

    const [isConfirmOpen, setConfirmOpen] = useState(false);

    return (
        <div className={styles.jobCard}>
            <div className={styles.job}>
                <BriefcaseBusiness className={styles.briefcase} size={18}/>
                <div className={styles.description}>
                    <span className={styles.career}>{career.careerName}</span>
                    <span className={styles.branch}>{career.careerBranch.name} ⋅ {dayjs(career.updatedAt).fromNow()}</span>
                </div>
            </div>

            <div className={styles.actions}>
                <Button
                    variant="level"
                    onClick={() => onLevelChange(career.simCareerId, career.level - 1)}
                    disabled={career.level === 1 || isUpdating}
                >
                    −
                </Button>

                <span className={styles.level}>{career.level}</span>

                <Button
                    variant="level"
                    onClick={() => onLevelChange(career.simCareerId, career.level + 1)}
                    disabled={career.level === 10 || isUpdating} // TODO get actual maximum
                >
                    +
                </Button>

                <Button variant="secondary" className={styles.remove} onClick={() => setConfirmOpen(true)}>
                    <X size={15}/>
                </Button>

                <ConfirmDialog
                    open={isConfirmOpen}
                    onOpenChange={setConfirmOpen}
                    onAction={() => onRemoveCareer(career.simCareerId)}
                    message={`Career "${career.careerName}" and its progress will be deleted.`}
                    actionLabel="Delete"
                />
            </div>
        </div>
    )
}

export default SimCareerCard;