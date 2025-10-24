import {useNavigate} from "react-router-dom";
import {useState} from "react";
import userRepository from "../../../../repository/userRepository";
import {Box, Button, Container, Paper, TextField, Typography} from "@mui/material";


const initialFormData = {
    "username": "",
    "password": "",
    "name": "",
    "surname": "",
};


const Register = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState(initialFormData);

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        userRepository
            .add(formData)
            .then(() => {
                console.log("The user is registered successfully!");
                setFormData(initialFormData);
                navigate("/login", {replace: true});
            })
            .catch((error) => console.log(error));
    };

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{padding: 4, mt: 8}}>
                <Typography variant="h5" align="center" gutterBottom>Register</Typography>
                <Box>
                    <TextField
                        fullWidth label="Name"
                        name="name"
                        margin="normal"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth label="Surname"
                        name="surname"
                        margin="normal"
                        value={formData.surname}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth label="Username"
                        name="username"
                        margin="normal"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth label="Password"
                        name="password"
                        type="password"
                        value={formData.password}
                        onChange={handleChange}
                        margin="normal"
                        required
                    />

                    <Button fullWidth variant="contained" type="submit" sx={{mt: 2}} onClick={handleSubmit}>
                        Register
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default Register;