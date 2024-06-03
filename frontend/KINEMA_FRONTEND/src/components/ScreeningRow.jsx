import React, { useContext } from 'react';
import "../styles/ScreeningRow.css";
import ScreeningTimeButton from './ScreeningTimeButton';
import AuthContext from './AuthContext';
import api from "../axiosAuth";
import "../styles/Button.css"

function ScreeningRow({ screeningId, cinemaHall, movie, startTime, endTime, format , onDelete}) {
  const { user } = useContext(AuthContext);

  const deleteScreening = async () => {
    try {
      await api.delete(`/api/screenings/${screeningId}`);
      alert('Screening deleted successfully!');
      onDelete(screeningId);
      // Optionally, refresh the screenings list or update the UI
    } catch (error) {
      console.error('Error deleting screening:', error);
    }
  };

  const duration = Math.floor((endTime - startTime) / (1000 * 60));
  const formattedStartTime = new Date(startTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

  return (
    <div className='screeningRowContainer'>
      <div className='screeningRow-image' style={{ backgroundImage: `url(${movie.posterPath})` }}></div>
      <div className='screeningRow-info'>
        <h1>{movie.title}</h1>
        <h3>{movie.movieGenre} | {duration} min | {format} | Hall: {cinemaHall.hallName}</h3>
        <ScreeningTimeButton time={formattedStartTime} screeningId={screeningId} />
        <h2>Director: {movie.director} | Production year: {movie.productionYear}</h2>
        <p>{movie.description}</p>
        {user && user.roles && user.roles.includes('ROLE_ADMIN') && (
        <button className='button_item' onClick={deleteScreening}>Delete Screening</button>
      )}
      </div>
    </div>
  );
}

export default ScreeningRow;


// import React from 'react';
// import "../styles/ScreeningRow.css";
// import ScreeningTimeButton from './ScreeningTimeButton';

// function ScreeningRow({ screeningId,cinemaHall, movie, startTime, endTime, format }) {
//   // Obliczamy czas trwania filmu
//   const duration = Math.floor((endTime - startTime) / (1000 * 60));

//   // Formatowanie daty/godziny
//   const formattedStartTime = new Date(startTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

//   return (
//     <div className='screeningRowContainer'>
//       <div className='screeningRow-image' style={{ backgroundImage: `url(${movie.posterPath})` }}></div>
//       <div className='screeningRow-info'>
//         <h1>{movie.title}</h1>
//         <h3>{movie.movieGenre} | {duration} min | {format} | Hall: {cinemaHall.hallName}</h3>
//         <ScreeningTimeButton time={formattedStartTime} screeningId={screeningId}/>
//         <h2>Director: {movie.director} | Production year: {movie.productionYear}</h2>
//         <p>{movie.description}</p>
//       </div>
//     </div>
//   );
// }

// export default ScreeningRow;