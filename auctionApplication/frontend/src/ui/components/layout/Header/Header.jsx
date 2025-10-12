import React from 'react';
import { Link } from "react-router-dom";
import {AppBar, Box, Button, Toolbar, Typography} from "@mui/material";
import "./Header.css"


const pages = [
    {"path": "/", "name": "home"},
    {"path": "/users", "name": "users"},
    {"path": "/items", "name": "items"},
    {"path": "/auctions", "name": "auctions"}
];

const Header = () => {
    return (
        <Box>
            <AppBar>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{mr: 3}}>
                        Items Auction
                    </Typography>

                    <Box sx={{flexGrow: 1, display: "flex"}}>
                        {pages.map((page) => (
                            <Link key={page.name} to={page.path}>
                                <Button
                                    sx={{my: 2, color: "white", display: "block", textDecoration: "none"}}>
                                    {page.name}
                                </Button>
                            </Link>
                        ))}

                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;