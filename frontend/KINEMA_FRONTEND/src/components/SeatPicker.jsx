import React, { useEffect, useState } from 'react';
import Seat from './Seat';
import api from '../axiosAuth';
import '../styles/SeatPicker.css';

const SeatPicker = ({ screeningId }) => {
  const [seats, setSeats] = useState([]);

  useEffect(() => {
    const fetchSeats = async () => {
      try {
        const response = await api.get(`/api/seat-availability/${screeningId}`);
        setSeats(response.data);
      } catch (error) {
        console.error('Error fetching seats:', error);
      }
    };

    fetchSeats();
  }, [screeningId]);

  const rows = {};
  seats.forEach(seat => {
    if (!rows[seat.seat.seatRow]) {
      rows[seat.seat.seatRow] = [];
    }
    rows[seat.seat.seatRow].push(seat);
  });

  const reserveSeat = async (seatId) => {
    try {
      const response = await api.post(`/api/seat-availability/reserve`, { screeningId, seatId });
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
                  row={seat.seat.seatRow}
                  column={seat.seat.seatColumn}
                  isAvailable={seat.isAvailable}
                  onClick={() => reserveSeat(seat.seat.id)}
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
