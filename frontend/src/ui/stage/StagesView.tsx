import {useEffect, useState} from "react";
import type {Stage} from "../../types/database.ts";
import {getAllStages} from "../../service/stageService.ts";

const StagesView = () => {
    const [stages, setStages] = useState<Stage[]>([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const fetchStages = async () => {
        try {
            setLoading(true);
            const data = await getAllStages();
            setStages(data);
        } catch (e: unknown) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        fetchStages();
    }, []);

    return (
        <div>
            {loading ?
                <p>Loading...</p> :
                <>
                    {error ?
                        <p>{error}</p> :
                        <ol>
                            {stages.map(stage => (
                                <li key={stage.stageId}>{stage.name}</li>
                            ))}
                        </ol>
                    }
                </>
            }
        </div>
    )
}

export default StagesView;