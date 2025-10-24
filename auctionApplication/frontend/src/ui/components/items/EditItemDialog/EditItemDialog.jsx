import {useEffect, useState} from "react";
import {Button, Box, Dialog, DialogActions, DialogContent, DialogTitle, TextField, Typography,} from "@mui/material";

const EditItemDialog = ({open, onClose, onEdit, item}) => {
    const [formData, setFormData] = useState({
        name: "",
        price: "",
        image: null,
        existingImageUrl: "",
    });

    useEffect(() => {
        if (item) {
            setFormData({
                name: item.name || "",
                price: item.price || "",
                image: null,
                existingImageUrl: item.imageUrl || "",
            });
        }
    }, [item]);

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleImageChange = (event) => {
        setFormData({...formData, image: event.target.files[0]});
    };

    const handleSubmit = () => {
        const itemData = {
            name: formData.name,
            price: formData.price,
        };

        const data = new FormData();
        data.append(
            "item",
            new Blob([JSON.stringify(itemData)], {type: "application/json"})
        );

        if (formData.image) {
            data.append("image", formData.image);
        }

        onEdit(item.id, data);
        onClose();
    };


    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Item</DialogTitle>
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
                    label="Price"
                    name="price"
                    type="number"
                    value={formData.price}
                    onChange={handleChange}
                    fullWidth
                />

                <Button
                    variant="outlined"
                    component="label"
                    sx={{mt: 2}}
                    // fullWidth
                >
                    Upload Image
                    <input type="file" hidden accept="image/*" onChange={handleImageChange}/>
                </Button>

                {formData.existingImageUrl && (
                    <Box sx={{mt: 2}}>
                        <Typography sx={{fontSize: "0.9rem", mb: 1}}>
                            Current image:
                        </Typography>
                        <Box
                            component="img"
                            src={`http://localhost:8080${formData.existingImageUrl}?t=${Date.now()}`}
                            alt="Preview"
                            sx={{
                                width: "100%",
                                maxHeight: 200,
                                objectFit: "contain",
                                borderRadius: 2,
                            }}
                        />
                    </Box>
                )}

                {formData.image && (
                    <Typography sx={{mt: 1, fontSize: "0.9rem"}}>
                        <strong>SELECTED NEW IMAGE: </strong>{formData.image.name}
                    </Typography>
                )}
            </DialogContent>

            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button
                    variant="contained"
                    color="warning"
                    className="submit-btn"
                    onClick={handleSubmit}
                >
                    Save Changes
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditItemDialog;
