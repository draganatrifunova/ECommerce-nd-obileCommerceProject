import React from 'react';
import {Grid} from "@mui/material";
import UserCard from "../UserCard/UserCard";

const UsersGrid = ({users, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {users.map((user) => (
                <Grid key={user.id} size={{xs: 12, sm: 6, md: 4, lg: 3}} display="flex">
                    <UserCard  user={user}  onEdit={onEdit}  onDelete={onDelete} />
                </Grid>
            ))}
        </Grid>
    );
};

export default UsersGrid;


//userCard   --> user, onEdit, onDelete