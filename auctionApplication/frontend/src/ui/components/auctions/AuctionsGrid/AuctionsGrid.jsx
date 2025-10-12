import {Grid} from "@mui/material";
import AuctionCard from "../AuctionCard/AuctionCard";

const AuctionsGrid = ({auctions}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {auctions.map((auction) => (
                <Grid key={auction.id} size={{xs: 12, sm: 6, md: 4, lg: 3}} display="flex">
                    <AuctionCard auction={auction}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default AuctionsGrid;