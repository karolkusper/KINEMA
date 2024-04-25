import React, {useState} from 'react';
import Logo from "../assets/cinema_logo.png";
import {Link} from 'react-router-dom'
import '../styles/NavBar.css'
import MenuButton from './MenuButton';

import ReorderIcon from '@mui/icons-material/Reorder';

function NavBar() {

    const [openLinks, setOpenLinks] = useState(false);

    const toggleNavBar = ()=>{
        setOpenLinks(!openLinks);
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
            <MenuButton desc="/" name="Home"/>
            <MenuButton desc="/movies" name="Movies"/>
            <MenuButton desc="/about" name="About"/>
            <MenuButton desc="/contact" name="Contact"/>
            <button onClick={toggleNavBar}>
                <ReorderIcon />
            </button>
        </div>
    </div>
  )
}

export default NavBar