import React, { useContext } from 'react';
import AuthContext from './AuthContext';
import api from "../axiosAuth";
import "../styles/Button.css"

function MovieItem({ id, title, desc, director, productionYear, image,onDelete  }) {
  const { user } = useContext(AuthContext);
  console.log(user)

  const deleteMovie = async () => {
    try {
      await api.delete(`/api/movies/${id}`);
      alert('Movie deleted successfully!');
      onDelete(id);
      // Optionally, refresh the movies list or update the UI
    } catch (error) {
      console.error('Error deleting movie:', error);
    }
  };

  return (
    <div className='moviesItem'>
      <div style={{ backgroundImage: `url(${image})` }}></div>
      <h1>{title}</h1>
      <h2>{director}</h2>
      <h2>{productionYear}</h2>
      <p>{desc}</p>
      {user && user.roles && user.roles.includes('ROLE_ADMIN') && (
        <button className='button_item' onClick={deleteMovie}>Delete Movie</button>
      )}
    </div>
  );
}

export default MovieItem;


// import React from 'react'

// function MovieItem({title,desc,director,productionYear,image}) {
//   return (
//     <div className='moviesItem'>
//         <div style={{backgroundImage: `url(${image})`}}></div>
//         <h1>{title}</h1>
//         <h2>{director}</h2>
//         <h2>{productionYear}</h2>
//         <p>{desc}</p>
//     </div>
//   )
// }

// export default MovieItem