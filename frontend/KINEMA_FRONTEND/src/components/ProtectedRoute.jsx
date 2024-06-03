import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import AuthContext from './AuthContext';

const ProtectedRoute = ({ children, role }) => {
    const { user } = useContext(AuthContext);

    if (!user || (role && !user.roles.includes(role))) {
        return <Navigate to="/" />;
    }

    return children;
};

export default ProtectedRoute;
