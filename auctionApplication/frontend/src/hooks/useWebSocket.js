import {useEffect, useRef, useState} from "react";
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';


const useWebSocket = (url) => {
    const [isConnected, setIsConnected] = useState(false);
    const clientRef = useRef(null);


    useEffect(() => {
        console.log("Creating WebSocket connection to:", url);

        const client = new Client({
            brokerURL: url,
            webSocketFactory: () => new SockJS(url),
            reconnectDelay: 5000,
            onConnect: () => {
                setIsConnected(true);
                console.log('WebSocket Connected to:', url);
            },
            onDisconnect: () => {
                setIsConnected(false);
                console.log('WebSocket Disconnected from:', url);
            }
        });

        client.activate();
        clientRef.current = client;


        return () => {
            console.log('🧹 Cleaning up WebSocket connection');
            client.deactivate();
        };
    }, [url]);

    return {client: clientRef.current, isConnected};
};

export default useWebSocket;