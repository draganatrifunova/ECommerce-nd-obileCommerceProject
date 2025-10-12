import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";


const DeleteUserDialog = ({open, onClose, onDelete, user}) => {
    const handleSubmit = () => {
        onDelete(user.id);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete User</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{user.id}</strong>? This action cannot be undone.
                </DialogContentText>

                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button color="error" variant="contained" className="submit-btn" onClick={handleSubmit}>Delete</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    );
};

export default DeleteUserDialog;