import React from 'react';
import './App.css';
import Content from './pages/Content';
import Register from './pages/Register';
import Login from './pages/Login';
import './styles/Forms.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='register' element={<Register />} />
        <Route path='content/*' element={<Content />} />
      </Routes>
    </Router>
  );
}

export default App;