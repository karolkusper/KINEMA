import React, { useContext, useState } from 'react';
import Logo from "../assets/cinema_logo.png";
import { useNavigate } from 'react-router-dom';
import '../styles/NavBar.css';
import MenuButton from './MenuButton';
import AuthContext from './AuthContext';
import ReorderIcon from '@mui/icons-material/Reorder';

function NavBar() {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const [openLinks, setOpenLinks] = useState(false);

    const toggleNavBar = () => {
        setOpenLinks(!openLinks);
    };

    const handleLogout = () => {
        logout();
        navigate('/content/home');
    };

    return (
        <div className='navbar'>
            <div className='leftSide'>
                <img src={Logo} alt="Logo" />
                <h1>KINEMA</h1>
            </div>
            <div className={`rightSide ${openLinks ? 'open' : ''}`}>
                <MenuButton desc="/content/home" name="Home" />
                <MenuButton desc="/content/movies" name="Movies" />
                <MenuButton desc="/content/repertuar" name="Repertuar" />
                {user && <MenuButton desc="/content/profile" name="Profile" />}
                {user && user.roles.includes('ROLE_ADMIN') && (
                    <MenuButton desc="/admin/admin_panel" name="Admin Panel" />
                )}
                {user ? (
                    <button className="menuButton" onClick={handleLogout}>Logout</button>
                ) : (
                    <MenuButton desc="/" name="Login" />
                )}
            </div>
            <button className="toggleButton" onClick={toggleNavBar}>
                <ReorderIcon />
            </button>
        </div>
    );
}

export default NavBar;


// import React, { useContext, useState } from 'react';
// import Logo from "../assets/cinema_logo.png";
// import { useNavigate } from 'react-router-dom';
// import '../styles/NavBar.css';
// import MenuButton from './MenuButton';
// import AuthContext from './AuthContext';
// import ReorderIcon from '@mui/icons-material/Reorder';

// function NavBar() {
//     const { user, logout } = useContext(AuthContext);
//     const navigate = useNavigate();

//     const [openLinks, setOpenLinks] = useState(false);

//     const toggleNavBar = () => {
//         setOpenLinks(!openLinks);
//     };

//     const handleLogout = () => {
//         logout();
//         navigate('/content/home');
//     };

//     return (
//         <div className='navbar'>
//             <div className='leftSide' id={openLinks ? 'open' : 'close'}>
//                 <img src={Logo} alt="Logo" />
//                 <h1>KINEMA</h1>
//             </div>
//             <div className='rightSide'>
//                 <MenuButton desc="/content/home" name="Home" />
//                 <MenuButton desc="/content/movies" name="Movies" />
//                 <MenuButton desc="/content/repertuar" name="Repertuar" />
//                 {user && <MenuButton desc="/content/profile" name="Profile" />}
//                 {user && user.roles.includes('ROLE_ADMIN') && (
//                     <MenuButton desc="/admin/admin_panel" name="Admin Panel" />
//                 )}
//                 {user ? (
//                     <button className="menuButton" onClick={handleLogout}>Logout</button>
//                 ) : (
//                     <MenuButton desc="/" name="Login" />
//                 )}
//             </div>
//             {/* <button onClick={toggleNavBar}>
//                 <ReorderIcon />
//             </button> */}
//         </div>
//     );
// }

// export default NavBar;
