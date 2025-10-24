import React, {useContext, useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import "./ItemPage.css"
import useItems from "../../../hooks/useItem";
import ItemsGrid from "../../components/items/ItemsGrid/ItemsGrid"
import AddItemDialog from "../../components/items/AddItemDialog/AddItemDialog";
import AuthContext from "../../../contexts/authContext";

const ItemPage = () => {
    const {items, loading, onAdd, onEdit, onDelete} = useItems();

    const [addItemDialogOpen, setAddItemDialogOpen] = useState(false);

    const {user} = useContext(AuthContext);
    const roles = user?.roles || [];
    const isOwner = roles.includes("ROLE_OWNER");
    const isAdmin = roles.includes("ROLE_ADMIN");

    return (
        <>
            <Box>
                {(isOwner || isAdmin) && (
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => setAddItemDialogOpen(true)}
                    >
                        Add Item
                    </Button>
                )}

                {loading && <Box className="progress-box"><CircularProgress/></Box>}
                {!loading && <ItemsGrid items={items} onEdit={onEdit} onDelete={onDelete}/>}
            </Box>
            <AddItemDialog
                open={addItemDialogOpen}
                onClose={() => setAddItemDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default ItemPage;
