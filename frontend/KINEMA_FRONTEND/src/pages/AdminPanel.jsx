import React from 'react'
import AdminAddMovieForm from '../components/AdminAddMovieForm.jsx';
import NavBar from '../components/NavBar.jsx';
import Footer from '../components/Footer.jsx';
import "../styles/AdminPanel.css";
import AdminAddScreeningForm from '../components/AdminAddScreeningForm.jsx';
function AdminPanel() {
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
  )
}

export default AdminPanel