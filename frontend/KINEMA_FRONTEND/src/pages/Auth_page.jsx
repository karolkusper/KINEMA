// Auth_page.jsx
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Register from './Register';
import Login from './Login';
import '../styles/Forms.css'
function Auth_page() {
  return (
    <Routes>
      <Route exact path='login' element={<Login />} />
      <Route path='register' element={<Register />} />
    </Routes>
  );
}

export default Auth_page;

// import React from 'react'
// import {BrowserRouter as  Route, Routes} from 'react-router-dom'
// import Register from './Register'

// function Auth_page() {
//   return (
//     <div>
//         <Routes>
//             {/* <Route exact path='/login' element={<Movie />} /> */}
//             <Route path='/auth/register' element={<Register />} />
//         </Routes>
//     </div>
//   )
// }

// export default Auth_page