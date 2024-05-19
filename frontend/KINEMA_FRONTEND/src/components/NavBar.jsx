import React, {useState,useEffect} from 'react';
import Logo from "../assets/cinema_logo.png";
import {useNavigate} from 'react-router-dom'
import '../styles/NavBar.css'
import MenuButton from './MenuButton';

import ReorderIcon from '@mui/icons-material/Reorder';

function NavBar() {

    const navigate = useNavigate();

    const [openLinks, setOpenLinks] = useState(false);

    const toggleNavBar = ()=>{
        setOpenLinks(!openLinks);
    }

    const [token, setToken] = useState('');

    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        setToken(storedToken);
    }, []);


    const logout = () => {
        localStorage.removeItem('token');
        setToken('');
        navigate('/content/home');
    }
  return (
    <div className='navbar'>
        <div className='leftSide' id={openLinks ? 'open' : 'close'}>
            {/* <h1>KINEMA</h1> */}
            <img src={Logo} alt="Logo" />
            <h1>KINEMA</h1>
        </div>
        <div className='rightSide'>
            {/* <Link to="/">Home</Link>
            <Link to="/movies">Movies</Link>
            <Link to="/about">About</Link>
            <Link to="/contact">Contact</Link> */}
            <MenuButton desc="/content/home" name="Home"/>
            <MenuButton desc="/content/movies" name="Movies"/>
            <MenuButton desc="/content/repertuar" name="Repertuar"/>
            {token && <MenuButton desc="/content/profile" name="Profile"/>}
            {token ? (
                    <button className="menuButton" onClick={logout}>Logout</button>
                ) : (
                    <MenuButton desc="/" name="Login"/>
                )}
            {/* <button onClick={toggleNavBar}>
                <ReorderIcon />
            </button> */}
        </div>
    </div>
  )
}

export default NavBar