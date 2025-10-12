import useAuctions from "../../../hooks/useAuctions";
import {Box, CircularProgress} from "@mui/material";
import AuctionsGrid from "../../components/auctions/AuctionsGrid/AuctionsGrid";

const AuctionPage = () => {
    const {auctions, loading} = useAuctions();

    return (
        <>
            <Box className="auctions-box">
                {loading && <Box className="progress-box"><CircularProgress/></Box>}
                {!loading && <AuctionsGrid auctions={auctions}/>}
            </Box>
        </>
    );
};

export default AuctionPage;