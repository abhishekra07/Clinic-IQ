// context/AuthContext.js
import { createContext, useContext, useState } from 'react';
import axios from '../api/axiosConfig.js';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem('accessToken') || null,
  );
  const [refreshToken, setRefreshToken] = useState(
    localStorage.getItem('refreshToken') || null,
  );
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem('user')) || null,
  );
  const [loading, setLoading] = useState(false);

  const login = async (credentials) => {
    setLoading(true);
    try {
      const response = await axios.post('/api/auth/login', credentials);
      const { accessToken, refreshToken, userId, name, email, roles } =
        response.data;

      const user = { userId, name, email, roles };

      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('user', JSON.stringify(user));

      setAccessToken(accessToken);
      setRefreshToken(refreshToken);
      setUser(user);
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    localStorage.clear();
    setAccessToken(null);
    setRefreshToken(null);
    setUser(null);
  };

  const value = {
    accessToken,
    refreshToken,
    user,
    login,
    logout,
    loading,
    isAuthenticated: !!accessToken,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => useContext(AuthContext);
