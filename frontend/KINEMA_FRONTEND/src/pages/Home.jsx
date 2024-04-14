import React from 'react'
import {Link} from 'react-router-dom'
import '../styles/Home.css'
import BannerImage from '../assets/tasma.jpg'
function Home() {
  return (
    <div className='home'  style={{backgroundImage: `url(${BannerImage})`}}>
        <div className='headerContainer'>
            <h1>KINEMA - YOUR FAVOURITE CINEMA</h1>
        
            <p>Explore world of cinematography...</p>

            <Link to='/movies'>
                <button>
                    See our movies
                </button>
            </Link>
        </div>
    </div>
  )
}

export default Home