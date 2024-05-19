// Content.jsx
import React from 'react';
import NavBar from '../components/NavBar.jsx';
import Footer from '../components/Footer.jsx';
import Home from './Home.jsx';
import Movie from './Movies.jsx';
import Repertuar from './Repertuar.jsx';
import Profile from './Profile.jsx';
import SeatPicker from '../components/SeatPicker.jsx';
import { Route, Routes } from 'react-router-dom';
import '../App.css';

function Content() {
  return (
    <>
      <NavBar />
      <Routes>
        <Route path='home' element={<Home />} />
        <Route path='movies' element={<Movie />} />
        <Route path='repertuar' element={<Repertuar />} />
        <Route path='profile' element={<Profile />} />
        <Route path='seats' element={<SeatPicker cinemaHallId={1}/>} />
      </Routes>
      <Footer />
    </>
  );
}

export default Content;

// import React from 'react'
// import NavBar from '../components/NavBar.jsx'
// import Footer from '../components/Footer.jsx'
// import Home from './Home.jsx'
// import Movie from './Movies.jsx'
// import Repertuar from './Repertuar.jsx'
// import {BrowserRouter as Route, Routes} from 'react-router-dom'

// function Content() {
//   return (
//     <div>
//         <NavBar />
//         <Routes>
//             <Route path='/content//home' element={<Home />} />
//             <Route path='/content/movies' element={<Movie />} />
//             <Route path='/content/repertuar' element={<Repertuar />} />
//         </Routes>
//         <Footer/>
//     </div>
//   )
// }

// export default Content