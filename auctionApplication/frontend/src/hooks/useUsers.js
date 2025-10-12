import {useCallback, useEffect, useState} from "react";
import userRepository from "../repository/userRepository";

const initialState = {
    users: [],
    loading: true,
};

const useUsers = () => {
    const [state, setState] = useState(initialState);

    const fetch = useCallback(() => {
        setState(initialState);

        userRepository
            .findAll()
            .then((response) => {
                console.log("Response from backend:", response)
                setState({
                    users: response.data,
                    loading: false,
                });
            })
            .catch((error) => console.log(error))
    }, []);


    const onAdd = useCallback((data) => {
        userRepository
            .add(data)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch]);

    const onEdit = useCallback((id, data) => {
        userRepository
            .edit(id, data)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch])

    const onDelete = useCallback((id) => {
        userRepository
            .delete(id)
            .then(() => fetch())
            .catch((error) => console.log(error));
    }, [fetch])

    useEffect(() => {
        fetch()
    }, [fetch]);

    return {...state, onAdd, onEdit, onDelete};
};

export default useUsers;