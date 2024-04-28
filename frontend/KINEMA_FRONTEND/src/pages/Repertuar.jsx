import React from 'react'
import ScreeningRow from '../components/ScreeningRow'
import { MovieList } from '../variables/MovieList'
import '../styles/Repertuar.css'

function Repertuar() {
  return (
    <div>
        <div>Dni tygodnia</div>
        <div className='moviesContainer'>
            {MovieList.map((movie,key)=>{
                return <ScreeningRow 
                key={key}
                title={movie.title}
                desc={movie.desc}
                director={movie.director}
                productionYear={movie.rok_produkcji}
                image={movie.image}
                />
            })}
        </div>
    </div>
  )
}

export default Repertuar