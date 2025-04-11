import React from 'react';
import {
  Box,
  Grid,
  Paper,
  TextField,
  Typography,
  Button,
  Divider,
} from '@mui/material';
import { useAuth } from '../context/AuthContext'; // assuming this is your context
import { useNavigate } from 'react-router-dom';
const LoginPage = () => {
  const { login, loading } = useAuth();
  const navigate = useNavigate();
  const [credentials, setCredentials] = React.useState({
    username: '',
    password: '',
  });

  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await login(credentials);
      navigate('/');
    } catch (err) {
      console.error(err);
      alert('Login failed');
    }
  };

  return (
    <Grid container sx={{ minHeight: '100vh' }}>
      {/* Left side with image */}
      <Grid item xs={12} md={6}>
        <Box
          sx={{
            height: '100%',
            backgroundImage: `url('/images/hospital-illustration.jpg')`, // Add your own image or convert the AVIF
            backgroundRepeat: 'no-repeat',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
      </Grid>

      {/* Right side with login form */}
      <Grid item xs={12} md={6} component={Paper} elevation={6} square>
        <Box
          sx={{
            my: 8,
            mx: 4,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Typography
            component="h1"
            variant="h4"
            sx={{ fontWeight: 600, color: '#1976d2' }}
          >
            Welcome Back
          </Typography>
          <Typography variant="body1" sx={{ mb: 3, color: 'text.secondary' }}>
            Login to your medical dashboard
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 1, width: '100%', maxWidth: 360 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              label="Username"
              name="username"
              autoFocus
              value={credentials.username}
              onChange={handleChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              value={credentials.password}
              onChange={handleChange}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              disabled={loading}
            >
              {loading ? 'Logging in...' : 'Login'}
            </Button>
            <Divider sx={{ my: 2 }} />
            <Typography variant="body2" align="center">
              Forgot your password?
            </Typography>
          </Box>
        </Box>
      </Grid>
    </Grid>
  );
};

export default LoginPage;
