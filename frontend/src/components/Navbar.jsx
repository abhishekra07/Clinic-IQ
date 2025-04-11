import React from 'react';
import {
  AppBar,
  Toolbar,
  IconButton,
  InputBase,
  Box,
  Typography,
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import NotificationsIcon from '@mui/icons-material/Notifications';
import LanguageIcon from '@mui/icons-material/Language';
import AccountCircle from '@mui/icons-material/AccountCircle';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

const Navbar = ({ sidebarOpen, setSidebarOpen }) => {
  return (
    <AppBar
      position="fixed"
      sx={{ zIndex: (theme) => theme.zIndex.drawer + 1, bgcolor: '#1976d2' }}
    >
      <Toolbar sx={{ justifyContent: 'space-between' }}>
        {/* Left side: Hamburger + Logo */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          <IconButton
            onClick={() => setSidebarOpen(!sidebarOpen)}
            color="inherit"
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6">Medflex</Typography>
        </Box>

        {/* Center: Search */}
        <Box sx={{ flexGrow: 1, display: 'flex', justifyContent: 'center' }}>
          <InputBase
            placeholder="Search…"
            sx={{
              bgcolor: 'white',
              px: 2,
              py: 0.5,
              borderRadius: 1,
              width: '40%',
              color: 'black',
            }}
          />
        </Box>

        {/* Right side */}
        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          <LanguageIcon />
          <CalendarTodayIcon />
          <NotificationsIcon />
          <AccountCircle />
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
