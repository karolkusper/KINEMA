import React from 'react'
import {Link} from 'react-router-dom'
import '../styles/MenuButton.css'

function MenuButton(props) {
  return (
    <>
        <Link to={props.desc} className="menuButton">{props.name}</Link>
    </>
    
  )
}

export default MenuButton