import React from 'react';
import './App.css';
import Content from './pages/Content';
import Register from './pages/Register';
import Login from './pages/Login';
import AdminPanel from './pages/AdminPanel';
import ErrorPage from './pages/ErrorPage'; 
import './styles/Forms.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './components/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='register' element={<Register />} />
          <Route path='content/*' element={<Content />} />
          <Route path='admin/admin_panel' element={<AdminPanel />} />
          <Route path='/error' element={<ErrorPage />} />
          <Route path='*' element={<ErrorPage />} /> {/* Catch-all route for undefined paths */}
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
// import React from 'react';
// import './App.css';
// import Content from './pages/Content';
// import Register from './pages/Register';
// import Login from './pages/Login';
// import AdminPanel from './pages/AdminPanel';
// import './styles/Forms.css'
// import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

// function App() {
//   return (
//     <Router>
//       <Routes>
//         <Route path='/' element={<Login />} />
//         <Route path='register' element={<Register />} />
//         <Route path='content/*' element={<Content />} />
//         <Route path='admin/admin_panel' element={<AdminPanel />} />
//       </Routes>
//     </Router>
//   );
// }

// export default App;