import React, { useState } from 'react';
import { Box } from '@mui/material';
import Navbar from './Navbar';
import Sidebar from './Sidebar';

const Layout = ({ children }) => {
  const [sidebarOpen, setSidebarOpen] = useState(true);

  return (
    <>
      <Navbar sidebarOpen={sidebarOpen} setSidebarOpen={setSidebarOpen} />
      <Box sx={{ display: 'flex' }}>
        <Sidebar open={sidebarOpen} />
        <Box
          component="main"
          sx={{
            flexGrow: 1,
            padding: 3,
            marginTop: '64px', // navbar height
            marginLeft: sidebarOpen ? '240px' : '70px',
            transition: 'margin 0.3s ease-in-out', // ✅ smooth transition
          }}
        >
          {children}
        </Box>
      </Box>
    </>
  );
};

export default Layout;
