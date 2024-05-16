import React from 'react'
import AdminAddMovieForm from '../components/AdminAddMovieForm.jsx';
import NavBar from '../components/NavBar.jsx';
import Footer from '../components/Footer.jsx';
import "../styles/AdminPanel.css";
function AdminPanel() {
  return (
    <div>
        <NavBar/>
        <h1 className='title'>Witaj adminie!</h1>
        <AdminAddMovieForm/>
        <Footer/>
    </div>
  )
}

export default AdminPanel