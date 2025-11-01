import {useCallback, useEffect, useState} from "react";
import auctionRepository from "../repository/auctionRepository"; // or wherever your axios instance is defined

const useOffer = (auctionId) => {
    const [lastOffer, setLastOffer] = useState(null);
    const [loading, setLoading] = useState(false);

    const fetch = useCallback(() => {
        setLastOffer(null);
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
        auctionRepository
            .addOffer(auctionId, offerData)
            .then(((response) => {
                setLastOffer(response.data); // update with latest offer
                return fetch();
            }))
            .catch((error) => console.log(error));
    }, [auctionId, fetch]);

    useEffect(() => {
        fetch();
    }, [auctionId, fetch]);


    return {lastOffer, addOffer, loading};
};

export default useOffer;
