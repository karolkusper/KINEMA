import React from 'react'
import {MovieList} from '../variables/MovieList'
import MovieItem from '../components/MovieItem'
import Slider from '../components/Slider'
import '../styles/Movies.css'
function Movies() {
  return (
    <div className='movies'>
        <h1 className='moviesTitle'>Our movies</h1>
        <div className='sliderDiv'>
          <Slider />
        </div>
        
        <div className='moviesList'>
            {MovieList.map((movie,key)=>{
                return <MovieItem 
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

export default Movies