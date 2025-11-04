import useWebSocket from "./useWebSocket";
import {useEffect, useState} from "react";

const useAuctionWebSocket = (auctionId) => {
    const {client, isConnected} = useWebSocket('http://localhost:8080/ws');
    const [realTimeAuction, setRealTimeAuction] = useState(null);
    const [realTimeOffer, setRealTimeOffer] = useState(null);

    useEffect(() => {
        console.log('useAuctionWebSocket effect running:', {
            client: !!client,
            isConnected,
            auctionId
        });


        if (client && isConnected && auctionId) {
            client.subscribe(`/topic/auctions/${auctionId}`, (message) => {
                const auctionUpdate = JSON.parse(message.body);
                setRealTimeAuction(auctionUpdate);
            });

            client.subscribe(`/topic/offers/${auctionId}`, (message) => {
                const offerUpdate = JSON.parse(message.body);
                setRealTimeOffer(offerUpdate);
            });
        }

        return () => {

        };
    }, [client, isConnected, auctionId]);

    return {
        realTimeAuction, realTimeOffer, isConnected
    };
};

export default useAuctionWebSocket;