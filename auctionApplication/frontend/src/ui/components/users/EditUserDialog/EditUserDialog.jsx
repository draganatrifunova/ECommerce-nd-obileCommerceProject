import React, {useState} from 'react';

import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@mui/material";

const EditUserDialog = ({open, onClose, onEdit, user}) => {

    const [formData, setFormData] = useState({
        "name": user.name,
        "surname": user.surname,
        "username": user.username,
        "password": ""
    });

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(user.id, formData)
        onClose();
    };


    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Room</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    type="text"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    type="text"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Username"
                    name="username"
                    type="text"
                    value={formData.username}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Password"
                    name="password"
                    type="password"
                    value={formData.password}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button variant="contained" color="warning" className="submit-btn" onClick={handleSubmit}>Edit</Button>
            </DialogActions>
        </Dialog>
    );

};

export default EditUserDialog;


