import {useNavigate} from "react-router-dom";
import useAuth from "../../../../hooks/useAuth ";
import {Button} from "@mui/material";
import React from 'react';

const AuthenticationToggle = () => {

    const navigate = useNavigate();

    const {isLoggedIn, logout} = useAuth();

    const handleLogin = () => {
        navigate("/login");
    };

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <Button
            color="inherit"
            variant={!isLoggedIn ? "text" : "outlined"}
            onClick={!isLoggedIn ? handleLogin : handleLogout}
        >
            {!isLoggedIn ? "Login" : "Logout"}
        </Button>
    );
};

export default AuthenticationToggle;