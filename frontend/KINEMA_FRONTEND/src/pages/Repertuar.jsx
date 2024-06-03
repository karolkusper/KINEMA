import React, { useState, useEffect } from 'react';
import ScreeningRow from '../components/ScreeningRow';
import api from "../axiosAuth";
import '../styles/Repertuar.css';

function Repertuar() {
  const [screenings, setScreenings] = useState([]);

  useEffect(() => {
    const fetchScreenings = async () => {
      try {
        const response = await api.get('/api/screenings');
        setScreenings(response.data);
      } catch (error) {
        console.log('Error fetching Screenings:', error);
      }
    };

    fetchScreenings();
  }, []);

  return (
    <div>
      <div className='screeningsContainer'>
        {screenings.map((screening) => (
          <ScreeningRow
            key={screening.id}
            screeningId={screening.id}
            cinemaHall={screening.cinemaHall}
            movie={screening.movie}
            startTime={screening.startTime}
            endTime={screening.endTime}
            format={screening.format}
          />
        ))}
      </div>
    </div>
  );
}

export default Repertuar;
