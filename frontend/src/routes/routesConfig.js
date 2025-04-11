import Home from '../pages/Home.jsx';
import Login from '../pages/Login.jsx';
import PublicRoute from './PublicRoute.jsx';
import ProtectedRoute from './ProtectedRoute.jsx';

const routesConfig = [
  {
    path: '/signin',
    component: Login,
    layout: PublicRoute,
  },
  {
    path: '/dashboard',
    component: Home,
    layout: ProtectedRoute,
  },
  {
    path: '/profile',
    component: Home,
    layout: ProtectedRoute,
  },
  {
    path: '/',
    component: Home,
    layout: ProtectedRoute,
  },
];

export default routesConfig;
