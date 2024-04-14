import React from 'react'

function MovieItem({title,desc,director,productionYear,image}) {
  return (
    <div className='moviesItem'>
        <div style={{backgroundImage: `url(${image})`}}></div>
        <h1>{title}</h1>
        <h2>{director}</h2>
        <h2>{productionYear}</h2>
        <p>{desc}</p>
    </div>
  )
}

export default MovieItem