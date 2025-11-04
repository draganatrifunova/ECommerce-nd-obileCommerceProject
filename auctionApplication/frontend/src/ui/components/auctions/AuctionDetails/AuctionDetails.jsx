import {useNavigate, useParams} from "react-router-dom";
import useAuctionDetails from "../../../../hooks/useAuctionDetails";
import {Box, Breadcrumbs, Button, CircularProgress, Link, Paper, Stack, Typography} from "@mui/material";
import {ArrowBack} from "@mui/icons-material";
import {useContext, useEffect} from "react";
import AuthContext from "../../../../contexts/authContext";
import useItems from "../../../../hooks/useItem";
import useOffer from "../../../../hooks/useOffer";
import {useTheme} from '@mui/material/styles';
import useAuctionWebSocket from "../../../../hooks/useAuctionWebSocket";

const AuctionDetails = () => {
    const theme = useTheme();
    const navigate = useNavigate();
    const {id} = useParams();
    const {auction, fetch, onCancel, addVisitor, onStart} = useAuctionDetails(id);
    const {user: loggedInUser} = useContext(AuthContext);
    const {fetch: refreshItems} = useItems();
    const {lastOffer, fetch: refreshOffer, loading: offerLoading} = useOffer(id);


    const {realTimeAuction, realTimeOffer} = useAuctionWebSocket(id);

    const handleCancel = async () => {
        await onCancel(); // This cancels and refreshes auction details
        refreshItems(); // This refreshes the items list
    };

    useEffect(() => {
        if (id && auction?.status === "FINISHED") {
            refreshOffer();
        }
    }, [id, auction?.status, refreshOffer]);


    if (!auction) {
        return <Box className="progress-box"><CircularProgress/></Box>
    }

    const displayAuction = realTimeAuction || auction;
    const displayOffer = realTimeOffer || lastOffer;

    const isOrganizer = loggedInUser?.sub === displayAuction.organizerUsername;
    const isNotOrganizer = loggedInUser?.sub !== displayAuction.organizerUsername;
    const hasJoined = displayAuction.visitors_username?.includes(loggedInUser?.sub);

    return (
        <Box width={750} mx="auto" mt={3}>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/auctions")
                    }}
                >
                    Auctions
                </Link>
                <Typography color="text.primary">{displayAuction.id}</Typography>
            </Breadcrumbs>

            <Paper elevation={12} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>
                    <Typography variant="h4" fontWeight={600}>
                        ID: {displayAuction.id}
                    </Typography>

                    <Typography variant="h6" color="text.secondary" sx={{fontStyle: 'italic'}}>
                        STATUS: {displayAuction.status}
                    </Typography>

                    <Box display={"flex"} justifyContent={"space-between"} sx={{fontStyle: 'italic'}}>
                        <Stack spacing={1}>
                            <Typography color="primary" variant="h6">
                                Item Info:
                            </Typography>
                            <Typography color="primary" variant="h6">
                                <span
                                    style={{fontWeight: 'bold'}}>[ID {displayAuction.item_id}]:</span> {displayAuction.itemName}
                            </Typography>
                        </Stack>
                        <Stack>
                            <Typography color="primary" variant="h6">
                                Organizer Info:
                            </Typography>
                            <Typography color="primary" variant="h6">
                                <span
                                    style={{fontWeight: 'bold'}}>[ID {displayAuction.organizer_id}]:</span> {displayAuction.organizerName} {displayAuction.organizerSurname}
                            </Typography>
                        </Stack>
                    </Box>

                    <Typography variant="h6" color="text.secondary">
                        <strong>Start: </strong> {displayAuction.timeStarting ? new Date(displayAuction.timeStarting).toLocaleString() : "Not yet started"}
                    </Typography>

                    <Typography variant="h6" color="text.secondary">
                        <strong>End: </strong> {displayAuction.timeFinishing ? new Date(displayAuction.timeFinishing).toLocaleString() : "Not yet ended"}
                    </Typography>

                    {((displayAuction.status === "STARTED") && (isOrganizer || hasJoined)) && (
                        <Box display="flex" justifyContent="center" mt={1}>
                            <Button
                                variant="contained"
                                color="success"
                                sx={{width: '200px'}}
                                onClick={() => navigate(`/auctions/${displayAuction.id}/start`)}
                            >
                                Go to Auction
                            </Button>
                        </Box>
                    )}

                    {(displayAuction.status === "FINISHED" && displayOffer?.lastUsername != null &&
                        displayOffer?.lastPrice != null &&
                        <Box display="flex" justifyContent="center" flexDirection="column"
                             sx={{p: 5, border: '4px solid #ccc', borderRadius: 2}}>
                            <Typography color="error" variant="h4" sx={{textAlign: 'center', fontWeight: "bold"}}>
                                SOLD!!!
                            </Typography>
                            <Typography sx={{
                                textAlign: 'center',
                                mt: 2,
                                fontStyle: "italic",
                                color: theme.palette.success.main
                            }} variant="h6">
                                Congratulations! The winning bidder is {displayOffer?.lastUsername} with a final offer
                                of {displayOffer?.lastPrice}$.
                            </Typography>
                        </Box>
                    )}

                    <Stack direction="row" justifyContent="space-between" spacing={2} mt={2}>
                        {(isOrganizer &&
                            <Box>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    className="reserve-btn"
                                    sx={{mr: 1}}
                                    disabled={displayAuction.status !== "RESERVED"}
                                    onClick={() => {
                                        if (!displayAuction.visitors_username || displayAuction.visitors_username.length === 0) {
                                            alert("Sorry, you cannot start the auction. No visitors have registered yet.");
                                        } else {
                                            onStart();
                                            navigate(`/auctions/${displayAuction.id}/start`);
                                        }
                                    }}
                                >
                                    Start Auction
                                </Button>

                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={handleCancel}
                                    disabled={displayAuction.status !== "RESERVED"}
                                >
                                    Cancel Auction
                                </Button>
                            </Box>
                        )}

                        {(isNotOrganizer &&
                            <Button
                                variant="contained"
                                color="primary"
                                disabled={(displayAuction.status !== "RESERVED") || hasJoined}
                                onClick={addVisitor}
                            >
                                {hasJoined ? "JOINED" : "JOIN NOW"}
                            </Button>
                        )}
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/auctions")}
                        >
                            Back to Auctions
                        </Button>
                    </Stack>
                </Stack>
            </Paper>
        </Box>
    )
};

export default AuctionDetails;