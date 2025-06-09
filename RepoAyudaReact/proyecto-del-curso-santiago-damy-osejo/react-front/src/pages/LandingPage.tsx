import { Button, Container, Typography, AppBar, Toolbar, Box } from "@mui/material";

const LandingPage = () => {
    return (
        <div className="min-h-screen bg-gradient-to-br from-blue-100 to-blue-300 flex flex-col">
            <AppBar position="static" color="transparent" elevation={0}>
                <Toolbar>
                    <Typography variant="h6" className="flex-1 font-bold text-blue-700">
                        MyApp
                    </Typography>
                    <Button color="primary" variant="outlined" className="mr-2">
                        Login
                    </Button>
                    <Button color="primary" variant="contained">
                        Sign Up
                    </Button>
                </Toolbar>
            </AppBar>
            <Container maxWidth="md" className="flex-1 flex flex-col justify-center items-center text-center">
                <Typography variant="h2" className="font-extrabold text-blue-800 mb-4">
                    Welcome to MyApp
                </Typography>
                <Typography variant="h5" className="mb-8 text-blue-600">
                    The best place to manage your projects efficiently and easily.
                </Typography>
                <Box>
                    <Button
                        variant="contained"
                        color="primary"
                        size="large"
                        className="mr-4"
                    >
                        Get Started
                    </Button>
                    <Button
                        variant="outlined"
                        color="primary"
                        size="large"
                    >
                        Learn More
                    </Button>
                </Box>
            </Container>
            <footer className="py-4 text-center text-blue-700">
                <Typography variant="body2">
                    © {new Date().getFullYear()} MyApp. All rights reserved.
                </Typography>
            </footer>
        </div>
    );
};

export default LandingPage;