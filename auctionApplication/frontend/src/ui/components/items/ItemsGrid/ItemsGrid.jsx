import React from 'react';
import {Box, Grid} from "@mui/material";
import ItemCard from "../ItemCard/ItemCard";

const ItemsGrid = ({items, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}} justifyContent="flex-start">
            {items.map((item) => (
                <Grid
                    key={item.id}
                    item
                    xs={12}
                    sm={8}
                    md={6}
                    lg={4}
                    display="flex"
                    justifyContent="center"
                >
                    <Box  width="75%" mx="auto">
                        <ItemCard item={item} onEdit={onEdit} onDelete={onDelete}/>
                    </Box>
                </Grid>
            ))}
        </Grid>
    );
};

export default ItemsGrid;