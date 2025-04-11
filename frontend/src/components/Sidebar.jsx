import React, { useState } from 'react';
import {
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Collapse,
  Box,
  Avatar,
  Typography,
  Tooltip,
} from '@mui/material';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import PhoneIcon from '@mui/icons-material/Phone';
import MedicalServicesIcon from '@mui/icons-material/MedicalServices';

const Sidebar = ({ open }) => {
  const [doctorMenuOpen, setDoctorMenuOpen] = useState(true);

  return (
    <Drawer
      variant="permanent"
      sx={{
        width: open ? 240 : 70,
        flexShrink: 0,
        whiteSpace: 'nowrap',
        transition: 'width 0.3s ease-in-out', // ✅ smooth drawer toggle
        '& .MuiDrawer-paper': {
          width: open ? 240 : 70,
          transition: 'width 0.3s ease-in-out', // ✅ drawer content transition
          overflowX: 'hidden',
        },
      }}
    >
      <Box sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
        {/* Profile Section */}
        <Box sx={{ p: 2, display: 'flex', alignItems: 'center', gap: 2 }}>
          <Avatar alt="Nick Gonzalez" src="/avatar.jpg" />
          {open && (
            <Box>
              <Typography variant="subtitle1">Nick Gonzalez</Typography>
              <Typography variant="body2">Dept Admin</Typography>
            </Box>
          )}
        </Box>

        {/* Menu Items */}
        <List>
          <Tooltip
            title="Hospital Dashboard"
            placement="right"
            disableHoverListener={open}
          >
            <ListItem button>
              <ListItemIcon>
                <DashboardIcon />
              </ListItemIcon>
              {open && <ListItemText primary="Hospital Dashboard" />}
            </ListItem>
          </Tooltip>

          <Tooltip
            title="Medical Dashboard"
            placement="right"
            disableHoverListener={open}
          >
            <ListItem button>
              <ListItemIcon>
                <MedicalServicesIcon />
              </ListItemIcon>
              {open && <ListItemText primary="Medical Dashboard" />}
            </ListItem>
          </Tooltip>

          <Tooltip
            title="Doctors"
            placement="right"
            disableHoverListener={open}
          >
            <ListItem button onClick={() => setDoctorMenuOpen(!doctorMenuOpen)}>
              <ListItemIcon>
                <LocalHospitalIcon />
              </ListItemIcon>
              {open && <ListItemText primary="Doctors" />}
              {open && (doctorMenuOpen ? <ExpandLess /> : <ExpandMore />)}
            </ListItem>
          </Tooltip>

          <Collapse in={doctorMenuOpen} timeout="auto" unmountOnExit>
            <List component="div" disablePadding sx={{ pl: open ? 4 : 2 }}>
              <ListItem button>
                {open && <ListItemText primary="Doctors Dashboard" />}
              </ListItem>
              <ListItem button>
                {open && <ListItemText primary="Doctors List" />}
              </ListItem>
              <ListItem button>
                {open && <ListItemText primary="Doctors Cards" />}
              </ListItem>
            </List>
          </Collapse>
        </List>

        {/* Emergency Contact */}
        <Box sx={{ mt: 'auto', p: 2 }}>
          <Box
            sx={{
              bgcolor: 'green',
              color: 'white',
              p: 1.5,
              borderRadius: 1,
              textAlign: 'center',
              fontWeight: 'bold',
            }}
          >
            <PhoneIcon sx={{ mr: 1 }} />
            0987654321
          </Box>
        </Box>
      </Box>
    </Drawer>
  );
};

export default Sidebar;
