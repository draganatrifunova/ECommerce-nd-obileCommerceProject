import {useCallback, useEffect, useState} from "react";
import itemRepository from "../repository/itemRepository";

const useItemDetails = (id) => {
    const [item, setItem] = useState(null);

    const fetch = useCallback(() => {
        itemRepository
            .findById(id)
            .then((response) => {
                setItem(response.data)
            })
            .catch((error) => console.log(error));
    }, [id])

    useEffect(() => {
        itemRepository
            .findById(id)
            .then((response) => setItem(response.data))
            .catch((error) => console.log(error));
    }, [id]);

    return {item, fetch};
};

export default useItemDetails;