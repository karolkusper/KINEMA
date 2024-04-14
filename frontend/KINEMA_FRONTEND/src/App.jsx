import './App.css'
import NavBar from './components/NavBar.jsx'
import Footer from './components/Footer.jsx'
import Home from './pages/Home.jsx'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

function App() {
 
  return (
    <>
    <Router>
      <NavBar />
      <Routes>
        <Route exact path='/' element={<Home />} />
      </Routes>
      <Footer/>
   </Router>
    </>
  )
}

export default App
