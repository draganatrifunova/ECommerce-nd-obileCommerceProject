import instance from "../custom-axios/axios";
import {useCallback, useEffect, useState} from "react";
import itemRepository from "../repository/itemRepository";

const initialState = {
    items: [],
    loading: true,
};

const useItems = () => {
    const [state, setState] = useState(initialState);

    const fetch = useCallback(() => {
        setState(initialState);
        itemRepository
            .listAll()
            .then((response) => setState({
                items: response.data,
                loading: false,
            }))
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        itemRepository
            .add(data)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch]);

    const onEdit = useCallback((id, data) => {
        itemRepository
            .update(id, data)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch]);

    const onDelete = useCallback((id) => {
        itemRepository
            .delete(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch])

    useEffect(() => {
        fetch();
    }, [fetch]);

    return {...state, fetch, onAdd, onEdit, onDelete};

};

export default useItems;