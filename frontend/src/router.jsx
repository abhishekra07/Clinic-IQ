import React from 'react';
import { Routes, Route } from 'react-router-dom';

const AppRouter = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/" element={<App />} />
    </Routes>
  );
};

export default AppRouter;
