import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useUsers from "../../../hooks/useUsers"
import UsersGrid from "../../components/users/UsersGrid/UsersGrid";
import AddUserDialog from "../../components/users/AddUserDialog/AddUserDialog";


const UserPage = () => {
    //    return {users, loading, onAdd, onEdit, onDelete};
    const {users, loading, onAdd, onEdit, onDelete} = useUsers();

    const [addUserDialogOpen, setAddUserDialogOpen] = useState(false);

    return (
        <>
            <Box>

                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setAddUserDialogOpen(true)}
                >
                    Add User
                </Button>


                {loading && <Box className="progress-box"><CircularProgress/></Box>}
                {!loading && <UsersGrid users={users} onEdit={onEdit} onDelete={onDelete}/>}
            </Box>

            <AddUserDialog
                open={addUserDialogOpen}
                onClose={() => setAddUserDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default UserPage;