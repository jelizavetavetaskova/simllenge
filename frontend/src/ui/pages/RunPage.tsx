import {useEffect, useState} from "react";
import type {Run, Sim} from "../../types/database.ts";
import {useParams} from "react-router-dom";
import {getRunById} from "../../service/runService.ts";
import * as Dialog from "@radix-ui/react-dialog";
import {X} from "lucide-react";
import CreateSimForm from "../components/sim/CreateSimForm.tsx";
import {getSimsByRun} from "../../service/simService.ts";
import SimCard from "../components/sim/SimCard.tsx";


const RunPage = () => {
    const [run, setRun] = useState<Run|null>(null);

    const [sims, setSims] = useState<Sim[]>([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const [modalOpen, setModalOpen] = useState(false);

    const {challengeId, runId} = useParams();

    const fetchSims = async () => {
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
            setSims(await getSimsByRun(challengeId, runId));
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
            if (!challengeId) {
                setError("Challenge id is required");
                return;
            }

            try {
                setLoading(true);
                setRun(await getRunById(challengeId, runId));
                setSims(await getSimsByRun(challengeId, runId));
            } catch (e) {
                (e instanceof Error) ? setError(e.message) : setError(String(e));
            } finally {
                setLoading(false);
            }
        }

        fetchData();
    }, [challengeId, runId]);



    return (
        <div>
            {loading ? (
                <p>Loading...</p>
            ) : error ? (
                <p>{error}</p>
            ) : run && (
                <>
                    <h1>Run: {run.stage.name}, {run.budget}</h1>

                    <div>
                        {sims.map(sim => (
                            <SimCard sim={sim} key={sim.simId} />
                        ))}
                    </div>

                    <button onClick={() => setModalOpen(true)}>Add a sim</button>

                    <Dialog.Root open={modalOpen} onOpenChange={setModalOpen}>
                        <Dialog.Portal>
                            <Dialog.Overlay className="overlay" />

                            <Dialog.Content className="content">
                                <Dialog.Close asChild>
                                    <button><X size={15}/></button>
                                </Dialog.Close>

                                <Dialog.Title>Create a sim</Dialog.Title>
                                <CreateSimForm challengeId={challengeId} runId={runId} onSuccess={fetchSims} onClose={() => setModalOpen(false)}/>
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