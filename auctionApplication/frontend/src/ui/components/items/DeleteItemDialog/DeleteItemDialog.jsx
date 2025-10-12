import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";


const DeleteItemDialog = ({open, onClose, onDelete, item}) => {

    const handleSubmit = () => {
        onDelete(item.id);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete Item</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>[{item.id}]: {item.name}</strong>? This action cannot be undone.
                </DialogContentText>

                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button color="error" variant="contained" onClick={handleSubmit}>Delete</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    );
};

export default DeleteItemDialog;
