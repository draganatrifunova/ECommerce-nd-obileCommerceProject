import {useCallback, useEffect, useState} from "react";
import auctionRepository from "../repository/auctionRepository";

const initialState = {
    auctions: [],
    loading: true,
};

const useAuctions = () => {
    const [state, setState] = useState(initialState);

    const fetch = useCallback(() => {
        setState(initialState);
        auctionRepository
            .listAll()
            .then((response) => setState({
                auctions: response.data,
                loading: false,
            }))
            .catch((error) => console.log(error));

    }, []);

    useEffect(() => {
        fetch();
    }, [fetch]);

    return {...state};
};

export default useAuctions;