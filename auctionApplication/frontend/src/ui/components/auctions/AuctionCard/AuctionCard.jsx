import {Card, CardActions, CardContent, Typography} from '@mui/material';

const AuctionCard = ({auction}) => {
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
            <CardContent sx={{ pb: 0 }}>
                <Typography>
                    {auction.id}
                </Typography>

                <Typography>

                </Typography>
            </CardContent>

            <CardActions>

            </CardActions>
        </Card>
    );
};

export default AuctionCard;