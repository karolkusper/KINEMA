import React from 'react';
import "../styles/ScreeningRow.css";
import ScreeningTimeButton from './ScreeningTimeButton';

function ScreeningRow({ screeningId,cinemaHall, movie, startTime, endTime, format }) {
  // Obliczamy czas trwania filmu
  const duration = Math.floor((endTime - startTime) / (1000 * 60));

  // Formatowanie daty/godziny
  const formattedStartTime = new Date(startTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

  return (
    <div className='screeningRowContainer'>
      <div className='screeningRow-image' style={{ backgroundImage: `url(${movie.posterPath})` }}></div>
      <div className='screeningRow-info'>
        <h1>{movie.title}</h1>
        <h3>{movie.movieGenre} | {duration} min | {format} | Hall: {cinemaHall.hallName}</h3>
        <ScreeningTimeButton time={formattedStartTime} screeningId={screeningId}/>
        <h2>Director: {movie.director} | Production year: {movie.productionYear}</h2>
        <p>{movie.description}</p>
      </div>
    </div>
  );
}

export default ScreeningRow;