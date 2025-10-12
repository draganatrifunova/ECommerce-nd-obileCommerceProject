import {useState} from "react";
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField,} from "@mui/material";

const initialFormData = {
    name: "",
    price: "",
    image: null,
};

const AddItemDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const [imageName, setImageName] = useState("");

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setFormData({...formData, image: file});
        setImageName(file ? file.name : "");
    };

    const handleSubmit = async () => {
        if (!formData.name || !formData.price || !formData.image) {
            alert("Please fill all fields and upload an image.");
            return;
        }

        // Prepare multipart form data
        const data = new FormData();
        data.append(
            "item",
            new Blob(
                [JSON.stringify({name: formData.name, price: parseInt(formData.price)})],
                {type: "application/json"}
            )
        );
        data.append("image", formData.image);

        await onAdd(data);
        setFormData(initialFormData);
        setImageName("");
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Item</DialogTitle>
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
                    variant="contained"
                    component="label"
                    sx={{mt: 2}}
                    color="primary"
                >
                    Upload Image
                    <input
                        type="file"
                        hidden
                        accept="image/*"
                        onChange={handleFileChange}
                    />
                </Button>

                {imageName && (
                    <p style={{marginTop: "8px", fontStyle: "italic", color: "#555"}}>
                        Selected file: {imageName}
                    </p>
                )}
            </DialogContent>

            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button
                    variant="contained"
                    color="primary"
                    className="submit-btn"
                    onClick={handleSubmit}
                >
                    Add
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddItemDialog;
