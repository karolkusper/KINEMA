import React from 'react'
import "../styles/ScreeningRow.css"
import ScreeningTimeButton from './ScreeningTimeButton'

function ScreeningRow({title,desc,director,productionYear,image}) {
  return (
    <div className='screeningRowContainer'>
        <div className='screeningRow-image' style={{backgroundImage: `url(${image})`}}></div>
        <div className='screeningRow-info'>
            <h1>{title}</h1>
            <h3>Gatunek | Czas-trwania</h3>
            <ScreeningTimeButton time="10:00"/>
            {/* <h2>{director}</h2>
            <h2>{productionYear}</h2>
            <p>{desc}</p> */}
        </div> 
    </div>
  )
}

export default ScreeningRow