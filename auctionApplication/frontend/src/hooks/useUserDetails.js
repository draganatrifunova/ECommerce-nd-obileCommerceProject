import {useCallback, useEffect, useState} from "react";

import userRepository  from "../repository/userRepository";


const useUserDetails = (id) => {
    const [user, setUser] = useState(null);

    const fetch = useCallback(() => {
        userRepository
            .findById(id)
            .then((response) => {
                setUser(response.data)
            })
            .catch((error) => console.log(error));
    }, [id])

    useEffect(() => {
        userRepository
            .findById(id)
            .then((response) => setUser(response.data))
            .catch((error) => console.log(error));
    }, [id]);

    return {user, fetch};
};

export default useUserDetails;