import React from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {Box, Breadcrumbs, Button, CircularProgress, Link, Paper, Stack, Typography} from "@mui/material";
import {ArrowBack} from "@mui/icons-material";

import useUserDetails from "../../../../hooks/useUserDetails";

const UserDetails = () => {


    const navigate = useNavigate();
    const {id} = useParams();
    const {user, fetch} = useUserDetails(id);

    if (!user)
        return <Box className="progress-box"><CircularProgress/></Box>;


    return (
        <Box width={750} mx="auto" mt={3}>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/users");
                    }}
                >
                    Users
                </Link>
                <Typography color="text.primary">{user.id}</Typography>
            </Breadcrumbs>

            <Paper elevation={12} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>
                    <Typography variant="h5" fontWeight="bold" color="primary">
                        User ID: {user.id}
                    </Typography>
                    <Stack spacing={1}>
                        <Typography variant="h6" color="text.secondary" fontWeight="bold">
                            Username: {user.username}
                        </Typography>

                        <Stack direction="row" spacing={1}>
                            <Typography variant="body1" color="text.secondary">
                                {user.name}
                            </Typography>

                            <Typography variant="body1" color="text.secondary">
                                {user.surname}
                            </Typography>
                        </Stack>
                    </Stack>

                    <Stack direction="row" justifyContent="space-between" spacing={2} mt={2}>
                        <Button
                            variant="contained"
                            color="primary">
                            Register as a visitor
                        </Button>

                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/users")}
                        >
                            Back to Users
                        </Button>
                    </Stack>
                </Stack>

            </Paper>
        </Box>
    );
};

export default UserDetails;


