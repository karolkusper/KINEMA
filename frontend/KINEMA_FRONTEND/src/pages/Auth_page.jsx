// Auth_page.jsx
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Register from './Register';
import Login from './Login';
import '../styles/Forms.css'
function Auth_page() {
  return (
    <div className='auth_body'>
      <Routes>
            <Route path='login' element={<Login />} />
            <Route path='register' element={<Register />} />
          </Routes>
    </div>
    
  );
}

export default Auth_page;
