import React from 'react';
import { Routes, Route } from 'react-router-dom';
import routesConfig from './routesConfig';

const AppRoutes = () => {
  return (
    <Routes>
      {routesConfig.map(({ path, component: Component, layout: Layout }) => (
        <Route
          key={path}
          path={path}
          element={
            Layout ? (
              <Layout>
                <Component />
              </Layout>
            ) : (
              <Component />
            )
          }
        />
      ))}
    </Routes>
  );
};

export default AppRoutes;
