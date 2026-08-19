import {useEffect, useState} from "react";
import type {Run, Sim} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getRunById} from "../../service/runService.ts";
import SimForm from "../components/sim/SimForm.tsx";
import {ageUp, getSimsByRun, markSimAsDead} from "../../service/simService.ts";
import SimCard from "../components/sim/SimCard.tsx";
import styles from "./RunPage.module.css";
import Button from "../components/shared/Button.tsx";
import Modal from "../components/shared/Modal.tsx";
import PageLayout from "../components/shared/PageLayout.tsx";


const RunPage = () => {
    const [run, setRun] = useState<Run|null>(null);

    const [sims, setSims] = useState<Sim[]>([]);
    const [editingSim, setEditingSim] = useState<Sim|null>(null);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const [modalOpen, setModalOpen] = useState(false);

    const {runId} = useParams();

    const alive: Sim[] = sims.filter(sim => sim.alive);
    const dead: Sim[] = sims.filter(sim => !sim.alive);

    const fetchSims = async () => {
        setError("");

        if (!runId) {
            setError("Run id is required");
            return;
        }

        try {
            setSims(await getSimsByRun(runId));
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    const handleEdit = (sim: Sim) => {
        setEditingSim(sim);
        setModalOpen(true);
    }

    const handleAgeUp = async (simId: number) => {
        try {
            await ageUp(simId);
            await fetchSims();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    const handleDeath = async (simId: number) => {
        try {
            await markSimAsDead(simId);
            await fetchSims();
        } catch (e) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            if (!runId) {
                setError("Run id is required");
                return;
            }

            try {
                setLoading(true);
                setRun(await getRunById(runId));
                setSims(await getSimsByRun(runId));
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchData();
    }, [runId]);

    return (
        <PageLayout
            heading={run?.challengeTitle ?? ""}
            action={
                <Button
                    variant="add"
                    type="button"
                    onClick={() => {
                        setEditingSim(null);
                        setModalOpen(true);
                    }}
                >
                    + Add a sim
                </Button>
            }
            subheading={
                run ?
                <div className={styles.runInfo}>
                    <p className={styles.stage}>Stage {run.stage.stageOrder} - {run.stage.name}</p>
                    <p className={styles.budget}>Budget: ${run.budget}</p>
                </div>
                    : undefined
            }
        >
            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : run && (
                <div>
                    <div className={styles.sim_block}>
                        <h2>Family</h2>
                        <div className={styles.cards}>
                            {alive.map(sim => (
                                <SimCard
                                    key={sim.simId}
                                    sim={sim}
                                    onEdit={() => handleEdit(sim)}
                                    onAgeUp={handleAgeUp}
                                    onDeath={handleDeath}
                                />
                            ))}
                        </div>
                    </div>

                    {dead.length > 0 &&
                        <div className={styles.sim_block}>
                            <h2>Deceased</h2>
                            <div className={`${styles.cards} ${styles.dead}`}>
                                {dead.map(sim => (
                                    <SimCard key={sim.simId} sim={sim}/>
                                ))}
                            </div>
                        </div>
                    }

                    <Modal open={modalOpen} onOpenChange={setModalOpen} title={editingSim ? "Edit sim" : "Create a sim"}>
                        <SimForm
                            runId={runId}
                            onSuccess={fetchSims}
                            onClose={() => {
                                setModalOpen(false);
                                setEditingSim(null);
                            }}
                            sim={editingSim ?? undefined}
                        />
                    </Modal>
                </div>
            )
            }
        </PageLayout>
    )
}

export default RunPage;