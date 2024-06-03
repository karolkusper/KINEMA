import React, { useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminAddMovieForm from '../components/AdminAddMovieForm.jsx';
import NavBar from '../components/NavBar.jsx';
import Footer from '../components/Footer.jsx';
import "../styles/AdminPanel.css";
import AdminAddScreeningForm from '../components/AdminAddScreeningForm.jsx';
import AuthContext from '../components/AuthContext';

function AdminPanel() {
  const { user } = useContext(AuthContext);

  const navigate = useNavigate();

  useEffect(() => {
    if (user) {
      console.log('Admin Panel User:', user);
    }
  }, [user]);

  if (!user) {
    return <div>Loading...</div>;
  }

  if (!user.roles.includes('ROLE_ADMIN')) {
    navigate("/error");
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

// import React from 'react'
// import AdminAddMovieForm from '../components/AdminAddMovieForm.jsx';
// import NavBar from '../components/NavBar.jsx';
// import Footer from '../components/Footer.jsx';
// import "../styles/AdminPanel.css";
// import AdminAddScreeningForm from '../components/AdminAddScreeningForm.jsx';
// function AdminPanel() {
//   return (
//     <div>
//         <NavBar/>
//         <h1 className='title'>Witaj adminie!</h1>
//         <div className='forms-container'>
//           <AdminAddMovieForm/>
//           <AdminAddScreeningForm/>
//         </div>
//         <Footer/>
//     </div>
//   )
// }

// export default AdminPanel