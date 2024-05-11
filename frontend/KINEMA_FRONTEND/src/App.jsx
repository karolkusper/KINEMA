// App.js
import React from 'react';
import './App.css';
import Auth_page from './pages/Auth_page';
import Content from './pages/Content';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='auth/*' element={<Auth_page />} />
        <Route path='content/*' element={<Content />} />
      </Routes>
    </Router>
  );
}

export default App;
// import './App.css'
// // import NavBar from './components/NavBar.jsx'
// // import Footer from './components/Footer.jsx'
// // import Home from './pages/Home.jsx'
// // import Movie from './pages/Movies.jsx'
// // import Repertuar from './pages/Repertuar.jsx'
// // import Register from './pages/Register.jsx'
// import Auth_page from './pages/Auth_page.jsx'
// import Content from './pages/Content.jsx'
// import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

// function App() {
 
//   return (
//     <>
//     <Router>
//       {/* <NavBar />
//       <Routes>
//         <Route exact path='/' element={<Home />} />
//         <Route exact path='/movies' element={<Movie />} />
//         <Route exact path='/repertuar' element={<Repertuar />} />
//       </Routes>
//       <Footer/> */}
//       <Routes>
//         <Route path='/auth/*' element={<Auth_page />} />
//         <Route path='/content/*' element={<Content />} />
//       </Routes>
//    </Router>
//     </>
//   )
// }

// export default App
