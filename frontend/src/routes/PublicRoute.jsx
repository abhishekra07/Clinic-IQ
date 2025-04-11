import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const PublicRoute = ({ children }) => {
  const { accessToken, loading } = useAuth();

  if (loading) return null;
  return accessToken ? <Navigate to="/" replace /> : children;
};

export default PublicRoute;
