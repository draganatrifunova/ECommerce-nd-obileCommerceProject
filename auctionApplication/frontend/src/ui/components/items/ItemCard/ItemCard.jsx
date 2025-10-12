import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import EditItemDialog from "../EditItemDialog/EditItemDialog";
import DeleteItemDialog from "../DeleteItemDialog/DeleteItemDialog";

const ItemCard = ({item, onEdit, onDelete}) => {
    const navigate = useNavigate();

    const [editItemDialogOpen, setEditItemDialogOpen] = useState(false);
    const [deleteItemDialogOpen, setDeleteItemDialogOpen] = useState(false);

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
                data-id={item.id}
            >
                <CardMedia
                    component="img"
                    height="200"
                    image={item.imageUrl ? `http://localhost:8080${item.imageUrl}` : "https://via.placeholder.com/200x200?text=No+Image"}
                    alt={item.name || "No image"}
                />
                <CardContent sx={{pb: 0}}>
                    <Typography gutterBottom variant="h5" component="div">
                        ID: {item.id}
                    </Typography>

                    <Typography variant="body1" sx={{mb: 1.5, fontWeight: 'bold'}}>
                        Item name:
                    </Typography>

                    <Typography variant="body1" color="text.secondary" sx={{mb: 1.5}}>
                        {item.name}
                    </Typography>
                </CardContent>

                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/items/${item.id}`)}>
                        Info
                    </Button>

                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditItemDialogOpen(true)}>
                            Edit
                        </Button>

                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteItemDialogOpen(true)}>
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <EditItemDialog
                open={editItemDialogOpen}
                onClose={() => setEditItemDialogOpen(false)}
                onEdit={onEdit}
                item={item}
            />
            <DeleteItemDialog
                open={deleteItemDialogOpen}
                onClose={() => setDeleteItemDialogOpen(false)}
                onDelete={onDelete}
                item={item}
            />

        </>
    );
};

export default ItemCard;