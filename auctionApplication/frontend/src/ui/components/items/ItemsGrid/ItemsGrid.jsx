import React from 'react';
import {Grid} from "@mui/material";
import ItemCard from "../ItemCard/ItemCard";

const ItemsGrid = ({items, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {items.map((item) => (
                <Grid key={item.id} size={{xs: 12, sm: 6, md: 4, lg: 3}} display="flex">
                    <ItemCard item={item} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default ItemsGrid;