import {useEffect, useState} from "react";
import type {Run, Sim} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getRunById} from "../../service/runService.ts";
import * as Dialog from "@radix-ui/react-dialog";
import {X} from "lucide-react";
import SimForm from "../components/sim/SimForm.tsx";
import {ageUp, getSimsByRun, markSimAsDead} from "../../service/simService.ts";
import SimCard from "../components/sim/SimCard.tsx";


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
        <div>
            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : run && (
                <>
                    <h1>Run: {run.stage.name}, {run.budget}</h1>

                    <button onClick={() => {
                        setEditingSim(null);
                        setModalOpen(true);
                    }}>
                        Add a sim
                    </button>

                    <div>
                        <h2>Family</h2>
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

                    {dead.length > 0 &&
                        <div>
                            <h2>Deceased</h2>
                            {dead.map(sim => (
                                <SimCard key={sim.simId} sim={sim}/>
                            ))}
                        </div>
                    }



                    <Dialog.Root open={modalOpen} onOpenChange={setModalOpen}>
                        <Dialog.Portal>
                            <Dialog.Overlay className="overlay" />

                            <Dialog.Content className="content">
                                <Dialog.Close asChild>
                                    <button><X size={15}/></button>
                                </Dialog.Close>

                                <Dialog.Title>{editingSim ? "Edit sim" : "Create a sim"}</Dialog.Title>
                                <SimForm
                                    runId={runId}
                                    onSuccess={fetchSims}
                                    onClose={() => {
                                        setModalOpen(false);
                                        setEditingSim(null);
                                    }}
                                    sim={editingSim ?? undefined}
                                />
                            </Dialog.Content>
                        </Dialog.Portal>
                    </Dialog.Root>
                </>
            )
            }
        </div>
    )
}

export default RunPage;