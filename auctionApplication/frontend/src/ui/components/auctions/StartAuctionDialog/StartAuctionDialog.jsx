import {useNavigate, useParams} from "react-router-dom";
import useAuctionDetails from "../../../../hooks/useAuctionDetails";
import {
    Box,
    Breadcrumbs,
    Button,
    CardMedia,
    CircularProgress,
    Link,
    Paper,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import useItemDetails from "../../../../hooks/useItemDetails";
import {useContext, useEffect, useState} from "react";
import AuthContext from "../../../../contexts/authContext";
import useOffer from "../../../../hooks/useOffer";
import userRepository from "../../../../repository/userRepository";
import {ArrowBack} from "@mui/icons-material";
import {useTheme} from '@mui/material/styles';
import useAuctionWebSocket from "../../../../hooks/useAuctionWebSocket";


const initialFormData = {
    price: ""
};

const StartAuctionDialog = () => {
    const theme = useTheme();
    const [formData, setFormData] = useState(initialFormData);
    const navigate = useNavigate();
    const {id} = useParams();
    const {auction, onFinish} = useAuctionDetails(id);
    const {item} = useItemDetails(auction?.item_id);
    const {user: loggedInUser} = useContext(AuthContext);
    const {lastOffer, addOffer, loading} = useOffer(id);
    const [visitors, setVisitors] = useState([]);


    const {realTimeAuction, realTimeOffer} = useAuctionWebSocket(id);


    useEffect(() => {
        if (auction?.visitors_username?.length > 0) {
            Promise.all(
                auction.visitors_username.map((username) =>
                    userRepository.findByUsername(username).then((res) => res.data)
                )
            )
                .then((users) => setVisitors(users))
                .catch((err) => console.error("Error loading visitors:", err));
        }
    }, [auction?.visitors_username]);


    if (!auction) {
        return <Box className="progress-box"><CircularProgress/></Box>
    }

    const displayAuction = realTimeAuction || auction;
    const displayOffer = realTimeOffer || lastOffer;

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };


    const handleSubmit = () => {
        const bidAmount = parseInt(formData.price);


        if (isNaN(bidAmount) || bidAmount <= 0) {
            alert("Please enter a valid bid amount.");
            return;
        }

        if (displayOffer?.lastUsername === loggedInUser?.sub) {
            alert("You already have the latest offer. Wait for another bidder before placing a new one.");
            return;
        }

        // Check if there is a last offer and compare prices
        if (displayOffer?.lastPrice != null && bidAmount <= displayOffer.lastPrice) {
            alert(`Your bid must be higher than the current highest bid of ${displayOffer.lastPrice}$.`);
            return;
        }

        addOffer(formData);
        setFormData(initialFormData);
    };

    const isOrganizer = loggedInUser?.sub === displayAuction.organizerUsername;
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
                        navigate(`/auctions/${displayAuction.id}`)
                    }}>
                    Auction Details
                </Link>
                <Typography color="text.primary">{displayAuction.id}</Typography>
            </Breadcrumbs>

            <Paper elevation={12} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={4}>
                    <Typography variant="h4" fontWeight={600} color="primary">
                        ID: {displayAuction.id}
                    </Typography>

                    <Typography variant="h5" color="text.secondary" sx={{fontStyle: 'italic'}}>
                        STATUS: {displayAuction.status}
                    </Typography>

                    <Stack spacing={1}>
                        <Typography color="text.secondary" variant="h5" sx={{fontStyle: 'italic'}}>
                            Item Information:
                        </Typography>
                        <Typography color="primary" variant="h5">
                            <span
                                style={{fontWeight: 'bold'}}>[ITEM ID {displayAuction.item_id}]:</span> {displayAuction.itemName}
                        </Typography>

                        <CardMedia
                            component="img"
                            image={item?.imageUrl ? `http://localhost:8080${item.imageUrl}?t=${Date.now()}` : "https://via.placeholder.com/200x200?text=No+Image"}
                            alt={item?.name || "No image"}
                            sx={{
                                width: "100%",
                                maxHeight: 400,
                                objectFit: "contain",
                                boxShadow: 2,
                                borderRadius: 4,
                                overflow: "hidden"
                            }}
                        />

                        <Typography variant="h5" color="primary">
                            STARTING PRICE: {item?.price}<strong>$</strong>
                        </Typography>
                    </Stack>

                    <Stack spacing={1}>
                        <Typography color="text.secondary" variant="h5" sx={{fontStyle: 'italic'}}>
                            Organizer Information:
                        </Typography>
                        <Typography color="primary" variant="h5">
                                <span
                                    style={{fontWeight: 'bold'}}>[ORGANIZER ID {displayAuction.organizer_id}]:</span> {displayAuction.organizerName} {displayAuction.organizerSurname}
                        </Typography>
                    </Stack>

                    <Typography variant="h5">
                        <strong>Start: </strong> {displayAuction.timeStarting ? new Date(displayAuction.timeStarting).toLocaleString() : "Not yet started"}
                    </Typography>

                    <Typography variant="h5">
                        <strong>End: </strong> {displayAuction.timeFinishing ? new Date(displayAuction.timeFinishing).toLocaleString() : "Not yet ended"}
                    </Typography>

                    <Box>
                        <Typography variant="h5" sx={{
                            mb: 2,
                            textAlign: 'center',
                            fontStyle: 'italic',
                            fontWeight: 'bold'
                        }}>VISITORS:</Typography>

                        {visitors.length > 0 ? (
                            <Stack spacing={3}>
                                {visitors.map((user) => (
                                    <Box key={user.id} sx={{p: 2, border: '1px solid #ccc', borderRadius: 2}}>
                                        <Typography variant="h6" sx={{fontWeight: 600}}>
                                            Username: {user.username}
                                        </Typography>
                                        <Typography color="text.secondary" variant="body1">
                                            {user.name} {user.surname}
                                        </Typography>
                                    </Box>
                                ))}
                            </Stack>
                        ) : (
                            <Typography color="text.secondary">No visitors yet.</Typography>
                        )}
                    </Box>


                    {(((isOrganizer || hasJoined) && displayOffer?.lastUsername != null &&
                            displayOffer?.lastPrice != null && displayAuction.status === "STARTED") &&
                        <Box>
                            <Typography variant="h5"
                                        sx={{mb: 2, textAlign: 'center', fontStyle: 'italic', fontWeight: 'bold'}}>LAST
                                OFFER:</Typography>
                            <Typography variant="h6" sx={{textAlign: 'center', fontStyle: 'italic'}}>
                                {displayOffer.lastUsername} just placed the latest bid of {displayOffer.lastPrice}$.
                            </Typography>
                        </Box>
                    )}


                    {(hasJoined &&
                        <Box>
                            <TextField
                                margin="dense"
                                label="Bid amount"
                                name="price"
                                type="number"
                                value={formData.price}
                                onChange={handleChange}
                                fullWidth
                            />
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={handleSubmit}
                                disabled={displayAuction.status !== "STARTED"}>
                                Place Bid
                            </Button>
                        </Box>
                    )}

                    {(isOrganizer && (displayOffer?.lastUsername != null && displayOffer?.lastPrice != null) && displayAuction.status === "STARTED" &&
                        <Box display="flex" justifyContent="center" mt={1}>
                            <Button
                                variant="contained"
                                color="success"
                                sx={{width: '200px'}}
                                onClick={onFinish}>
                                Sell
                            </Button>
                        </Box>
                    )}

                    {(displayAuction.status === "FINISHED" && (isOrganizer || hasJoined) &&
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

                    <Button
                        variant="outlined"
                        startIcon={<ArrowBack/>}
                        onClick={() => navigate(`/auctions/${id}`)}
                        sx={{width: '270px'}}
                    >
                        Back to Auction Details
                    </Button>
                </Stack>
            </Paper>
        </Box>
    )
};

export default StartAuctionDialog;