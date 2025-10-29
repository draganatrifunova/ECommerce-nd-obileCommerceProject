import {useNavigate, useParams} from "react-router-dom";
import useAuctionDetails from "../../../../hooks/useAuctionDetails";
import {Box, Breadcrumbs, Button, CircularProgress, Link, Paper, Stack, Typography} from "@mui/material";
import {ArrowBack} from "@mui/icons-material";
import {useContext} from "react";
import AuthContext from "../../../../contexts/authContext";
import useItems from "../../../../hooks/useItem";

const AuctionDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const {auction, fetch, onCancel, addVisitor, onStart} = useAuctionDetails(id);
    const {user: loggedInUser} = useContext(AuthContext);
    const {fetch: refreshItems} = useItems();

    const handleCancel = async () => {
        await onCancel(); // This cancels and refreshes auction details
        refreshItems(); // This refreshes the items list
    };


    if (!auction) {
        return <Box className="progress-box"><CircularProgress/></Box>
    }
    const isOrganizer = loggedInUser?.sub === auction.organizerUsername;
    const isNotOrganizer = loggedInUser?.sub !== auction.organizerUsername;
    const hasJoined = auction.visitors_username?.includes(loggedInUser?.sub);

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
                <Typography color="text.primary">{auction.id}</Typography>
            </Breadcrumbs>

            <Paper elevation={12} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>
                    <Typography variant="h4" fontWeight={600}>
                        ID: {auction.id}
                    </Typography>

                    <Typography variant="h6" color="text.secondary" sx={{fontStyle: 'italic'}}>
                        STATUS: {auction.status}
                    </Typography>

                    <Box display={"flex"} justifyContent={"space-between"} sx={{fontStyle: 'italic'}}>
                        <Stack spacing={1}>
                            <Typography color="primary" variant="h6">
                                Item Info:
                            </Typography>
                            <Typography color="primary" variant="h6">
                                <span style={{fontWeight: 'bold'}}>[ID {auction.item_id}]:</span> {auction.itemName}
                            </Typography>
                        </Stack>
                        <Stack>
                            <Typography color="primary" variant="h6">
                                Organizer Info:
                            </Typography>
                            <Typography color="primary" variant="h6">
                                <span
                                    style={{fontWeight: 'bold'}}>[ID {auction.organizer_id}]:</span> {auction.organizerName} {auction.organizerSurname}
                            </Typography>
                        </Stack>
                    </Box>

                    <Typography variant="h6" color="text.secondary">
                        <strong>Start: </strong> {auction.timeStarting ? new Date(auction.timeStarting).toLocaleString() : "Not yet started"}
                    </Typography>

                    <Typography variant="h6" color="text.secondary">
                        <strong>End: </strong> {auction.timeFinishing ? new Date(auction.timeFinishing).toLocaleString() : "Not yet ended"}
                    </Typography>

                    {((auction.status === "STARTED") && (isOrganizer || hasJoined)) && (
                        <Box display="flex" justifyContent="center" mt={1}>
                            <Button
                                variant="contained"
                                color="success"
                                sx={{width: '200px'}}
                                onClick={() => navigate(`/auctions/${auction.id}/start`)}
                            >
                                Go to Auction
                            </Button>
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
                                    disabled={auction.status !== "RESERVED"}
                                    onClick={() => {
                                        if (!auction.visitors_username || auction.visitors_username.length === 0) {
                                            alert("Sorry, you cannot start the auction. No visitors have registered yet.");
                                        } else {
                                            onStart();
                                            navigate(`/auctions/${auction.id}/start`);
                                        }
                                    }}
                                >
                                    Start Auction
                                </Button>

                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={handleCancel}
                                    disabled={auction.status !== "RESERVED"}
                                >
                                    Cancel Auction
                                </Button>
                            </Box>
                        )}

                        {(isNotOrganizer &&
                            <Button
                                variant="contained"
                                color="primary"
                                disabled={(auction.status !== "RESERVED") || hasJoined}
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