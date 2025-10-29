import {Button, Card, CardActions, CardContent, Typography} from '@mui/material';
import InfoIcon from "@mui/icons-material/Info";
import React from "react";
import {useNavigate} from "react-router-dom";

const AuctionCard = ({auction}) => {
    const navigate = useNavigate();

    return (
        <Card
            sx={{
                borderRadius: 2,
                p: 1,
                flexGrow: 1,
                display: "flex",
                flexDirection: "column",
                justifyContent: "space-between"
            }}
            data-id={auction.id}>
            <CardContent sx={{pb: 0}}>
                <Typography gutterBottom variant="h5" component="div">
                    ID: {auction.id}
                </Typography>

                <Typography variant="h6" sx={{mb: 1.5, fontWeight: 'bold', fontStyle: 'italic'}}>
                    STATUS: {auction.status}
                </Typography>

                <Typography variant="body1" color="text.secondary" sx={{mb: 1.5}}>
                    Item name: {auction.itemName}
                </Typography>

                <Typography variant="body1" color="text.secondary">
                    Organizer: {auction.organizerName}
                </Typography>
            </CardContent>

            <CardActions>
                <Button
                    size="small"
                    color="info"
                    startIcon={<InfoIcon/>}
                    sx={{ mt: 1 }}
                    onClick={() => navigate(`/auctions/${auction.id}`)}
                >
                    Info
                </Button>
            </CardActions>
        </Card>
    );
};

export default AuctionCard;