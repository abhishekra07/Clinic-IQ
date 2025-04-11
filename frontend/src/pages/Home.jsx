import React from 'react';
import { Typography, Button } from '@mui/material';
import { useAuth } from '../context/AuthContext';
import Layout from '../components/Layout';

const Home = () => {
  const { logout } = useAuth();

  return (
    <Layout>
      <Typography variant="h4">Welcome to the Dashboard</Typography>
      <Typography variant="body1" sx={{ marginTop: 2 }}>
        This is a protected route, accessible only after login.
      </Typography>
      <Button variant="outlined" onClick={logout} sx={{ mt: 2 }}>
        Logout
      </Button>
    </Layout>
  );
};

export default Home;
