import React, {useContext, useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from '@mui/material';
import {useNavigate} from "react-router-dom"
import EditUserDialog from "../EditUserDialog/EditUserDialog";
import DeleteUserDialog from "../DeleteUserDialog/DeleteUserDialog";
import AuthContext from "../../../../contexts/authContext";


const UserCard = ({user, onEdit, onDelete}) => {
    const navigate = useNavigate();

    const [editCardDialogOpen, setEditCardDialogOpen] = useState(false);
    const [deleteCardDialogOpen, setDeleteCardDialogOpen] = useState(false);

    const {user: loggedInUser} = useContext(AuthContext);
    const roles = loggedInUser?.roles || [];
    const isAdmin = roles.includes("ROLE_ADMIN");
    const isOwner = roles.includes("ROLE_OWNER");

    return (
        <>
            <Card
                sx={{
                    borderRadius: 2,
                    p: 1,
                    flexGrow: 1,
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "space-between"
                }}
                className="card"
                data-id={user.id}
            >

                <CardContent sx={{pb: 0}}>
                    <Typography gutterBottom variant="h5" component="div">
                        ID: {user.id}
                    </Typography>

                    <Typography variant="body1" sx={{mb: 1.5, fontWeight: 'bold'}}>
                        Username:
                    </Typography>

                    <Typography variant="body1" color="text.secondary" sx={{mb: 1.5}}>
                        {user.username}
                    </Typography>
                </CardContent>

                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/users/${user.id}`)}
                        className="info-item"
                    >
                        Info
                    </Button>
                    <Box>
                        {(isOwner || isAdmin) && (
                            <Button
                                size="small"
                                color="warning"
                                startIcon={<EditIcon/>}
                                sx={{mr: "0.25rem"}}
                                onClick={() => setEditCardDialogOpen(true)}
                                className="edit-item"
                            >
                                Edit
                            </Button>
                        )}
                        {isAdmin && (
                            <Button
                                size="small"
                                color="error"
                                startIcon={<DeleteIcon/>}
                                onClick={() => setDeleteCardDialogOpen(true)}
                                className="delete-item"
                            >
                                Delete
                            </Button>
                        )}

                    </Box>
                </CardActions>
            </Card>

            <EditUserDialog
                open={editCardDialogOpen}
                onClose={() => setEditCardDialogOpen(false)}
                onEdit={onEdit}
                user={user}
            />

            <DeleteUserDialog
                open={deleteCardDialogOpen}
                onClose={() => setDeleteCardDialogOpen(false)}
                onDelete={onDelete}
                user={user}
            />
        </>
    );
};

export default UserCard;