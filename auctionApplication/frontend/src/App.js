import './App.css';
import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Layout from "./ui/components/layout/Layout/Layout";
import HomePage from "./ui/pages/HomePage/HomePage";
import ItemPage from "./ui/pages/ItemPage/ItemPage";
import UserPage from "./ui/pages/UserPage/UserPage";
import UserDetails from "./ui/components/users/UserDetails/UserDetails"
import ItemDetails from "./ui/components/items/ItemDetails/ItemDetails"
import AuctionPage from "./ui/pages/AuctionPage/AuctionPage";
import AuctionDetails from "./ui/components/auctions/AuctionDetails/AuctionDetails";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="users" element={<UserPage/>}/>
                    <Route path="users/:id" element={<UserDetails/>}/>
                    <Route path="items" element={<ItemPage/>}/>
                    <Route path="items/:id" element={<ItemDetails/>}/>
                    <Route path="auctions" element={<AuctionPage/>}/>
                    <Route path="auctions/:id" element={<AuctionDetails/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
