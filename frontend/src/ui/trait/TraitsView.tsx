import {useEffect, useState} from "react";
import type {Trait} from "../../types/database.ts";
import {getAllTraits} from "../../service/traitService.ts";

const TraitsView = () => {
    const [traits, setTraits] = useState<Trait[]>([]);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const fetchTraits = async () => {
        try {
            setLoading(true);
            const data = await getAllTraits();
            setTraits(data);
        } catch (e: unknown) {
            (e instanceof Error) ? setError(e.message) : setError(String(e));
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        fetchTraits();
    }, []);

    return (
        <div>
        {loading ?
            <p>Loading...</p> :
            <>
                {error ?
                    <p>{error}</p> :
                    <ul>
                        {traits.map(trait => (
                            <li key={trait.traitId}>{trait.name}</li>
                        ))}
                    </ul>
                }
            </>
        }
        </div>
    )
}

export default TraitsView;