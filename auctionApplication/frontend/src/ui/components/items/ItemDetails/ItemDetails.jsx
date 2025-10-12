import {useNavigate, useParams} from "react-router-dom";
import useItemDetails from "../../../../hooks/useItemDetails";
import {Box, Breadcrumbs, Button, CardMedia, CircularProgress, Link, Paper, Stack, Typography} from "@mui/material";
import React from "react";
import {ArrowBack, Cancel, CheckCircle} from "@mui/icons-material";

const ItemDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const {item, fetch} = useItemDetails(id);

    if (!item) {
        return <Box className="progress-box"><CircularProgress/></Box>;
    }

    return (
        <Box width={750} mx="auto" mt={3}>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/items");
                    }}
                >
                    Items
                </Link>
                <Typography color="item.primary">{item.id}</Typography>
            </Breadcrumbs>

            <Paper elevation={12} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>

                    <CardMedia
                        component="img"
                        image={item.imageUrl ? `http://localhost:8080${item.imageUrl}` : "https://via.placeholder.com/200x200?text=No+Image"}
                        alt={item.name || "No image"}
                        sx={{
                            width: "100%",
                            maxHeight: 400,
                            objectFit: "contain",
                            boxShadow: 2,
                            borderRadius: 4,
                            overflow: "hidden"
                        }}
                    />

                    <Typography variant="h3" fontWeight="bold" align="center" color="primary">
                        {item.name}
                    </Typography>

                    <Typography variant="h5" fontWeight="bold" color="text.secondary">
                        ID: {item.id}
                    </Typography>

                    <Typography color="text.secondary" variant="h6" fontWeight="bold">
                        Price: {item.price}<strong>$</strong>
                    </Typography>

                    <Box display="flex" alignItems="center">
                        {item.available ? <CheckCircle color="success" sx={{mr: 1}}/> :
                            <Cancel color="error" sx={{mr: 1}}/>}
                        <Typography variant="h6" color={item.available ? "success.main" : "error.main"}
                                    className={item.available ? "available" : "not-available"}>
                            <strong className="room-available">{item.available ? "АVAILABLE" : "UNАVAILABLE"}</strong>
                        </Typography>
                    </Box>

                    <Stack direction="row" justifyContent="space-between" spacing={2} mt={2}>
                        <Box>
                            <Button
                                color="primary"
                                variant="contained"
                                sx={{mr: "1rem"}}
                            >
                                Create Auction
                            </Button>

                            <Button
                                color="primary"
                                variant="contained"
                            >
                                Add item to Auction
                            </Button>
                        </Box>

                        <Button
                            startIcon={<ArrowBack/>}
                            variant="outlined"
                            onClick={() => navigate("/items")}
                        >
                            Back to items
                        </Button>
                    </Stack>

                </Stack>
            </Paper>
        </Box>
    );
};

export default ItemDetails;