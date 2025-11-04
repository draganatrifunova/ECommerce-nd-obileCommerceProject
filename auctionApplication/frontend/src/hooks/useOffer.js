import {useCallback, useEffect, useState} from "react";
import auctionRepository from "../repository/auctionRepository"; // or wherever your axios instance is defined

const useOffer = (auctionId) => {
    const [lastOffer, setLastOffer] = useState(null);
    const [loading, setLoading] = useState(false);

    const fetch = useCallback(() => {
        setLoading(true);
        auctionRepository
            .getLastOffer(auctionId)
            .then((response) => {
                setLastOffer(response.data);
                setLoading(false);
            })
            .catch((error) => console.log(error));
    }, [auctionId]);

    const addOffer = useCallback((offerData) => {
        setLoading(true);
        return auctionRepository
            .addOffer(auctionId, offerData)
            .then(((response) => {
                setLoading(false);
                return response;
            }))
            .catch((error) => console.log(error));
    }, [auctionId]);

    useEffect(() => {
        fetch();
    }, [fetch]);


    return {lastOffer, addOffer, loading, fetch};
};

export default useOffer;
