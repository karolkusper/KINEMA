import React, { useContext, useEffect } from 'react';
import AdminAddMovieForm from '../components/AdminAddMovieForm.jsx';
import NavBar from '../components/NavBar.jsx';
import Footer from '../components/Footer.jsx';
import "../styles/AdminPanel.css";
import AdminAddScreeningForm from '../components/AdminAddScreeningForm.jsx';
import AuthContext from '../components/AuthContext';
import { Navigate } from 'react-router-dom';

function AdminPanel() {
  const { user} = useContext(AuthContext);

  useEffect(() => {
    if (user) {
      console.log('Admin Panel User:', user);
    }
  }, [user]);


  if (!user) {
    return <Navigate to="/" />;
  }

  if (!user.roles.includes('ROLE_ADMIN')) {
    return <Navigate to="/error" />;
  }

  return (
    <div>
      <NavBar/>
      <h1 className='title'>Witaj adminie!</h1>
      <div className='forms-container'>
        <AdminAddMovieForm/>
        <AdminAddScreeningForm/>
      </div>
      <Footer/>
    </div>
  );
}

export default AdminPanel;
