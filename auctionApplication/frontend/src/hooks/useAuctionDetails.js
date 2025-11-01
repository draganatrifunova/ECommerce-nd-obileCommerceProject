import {useCallback, useEffect, useState} from "react";
import auctionRepository from "../repository/auctionRepository";

const useAuctionDetails = (id) => {
    const [auction, setAuction] = useState(null);

    const fetch = useCallback(() => {
        auctionRepository
            .findById(id)
            .then((response) => {
                setAuction(response.data)
            })
            .catch((error) => console.log(error));
    }, [id])

    const onCancel = useCallback(() => {
        auctionRepository
            .cancelByIdAndOrganizer(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [id, fetch]);

    const addVisitor = useCallback(() => {
        auctionRepository
            .joinAuction(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [id, fetch]);

    const onStart = useCallback(() => {
        auctionRepository
            .startAuction(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [id, fetch]);

    const onFinish = useCallback(() => {
        auctionRepository
            .finishAuction(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [id, fetch]);

    useEffect(() => {
        fetch()
    }, [fetch]);

    return {auction, fetch, onCancel, addVisitor, onStart, onFinish};
};

export default useAuctionDetails;