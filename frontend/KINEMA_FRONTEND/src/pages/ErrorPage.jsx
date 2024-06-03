import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/ErrorPage.css'; 

const ErrorPage = () => {
    const navigate = useNavigate();

    const goHome = () => {
        navigate('/content/home');
    };

    return (
        <div className="error-page">
            <h1>Error</h1>
            <p>You do not have access to this page or an error has occurred.</p>
            <button onClick={goHome}>Go to Home</button>
        </div>
    );
};

export default ErrorPage;
