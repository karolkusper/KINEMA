import React, { useEffect, useState } from 'react';
import Seat from './Seat';
import api from '../axiosAuth';
import '../styles/SeatPicker.css';

const SeatPicker = ({ cinemaHallId }) => {
  const [seats, setSeats] = useState([]);

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        const response = await api.get(`/api/seats/${cinemaHallId}`);
        setSeats(response.data);
      } catch (error) {
        console.error('Error fetching seats:', error);
      }
    };

    fetchSeats();
  }, [cinemaHallId]);

  const rows = {};
  seats.forEach(seat => {
    if (!rows[seat.seatRow]) {
      rows[seat.seatRow] = [];
    }
    rows[seat.seatRow].push(seat);
  });


  const reserveSeat = async (seatId) => {
    try {
      const response = await api.post(`/api/seats/hall/${seatId}`);
      const updatedSeat = response.data;
      setSeats(prevSeats => 
        prevSeats.map(seat => seat.id === updatedSeat.id ? updatedSeat : seat)
      );
    } catch (error) {
      console.error('Error reserving seat:', error);
    }
  };


  return (
    <div className="seat-picker-container">
      <div className="seat-picker">
        {Object.keys(rows).map(row => (
          <div key={row} className="row">
            <div className="row-number">{row}</div>
            <div className="seats">
              {rows[row].map(seat => (
                <Seat 
                  key={seat.id}
                  row={seat.seatRow}
                  column={seat.seatColumn}
                  isAvailable={seat.available}
                  onClick={() => reserveSeat(seat.id)}
                />
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default SeatPicker;
