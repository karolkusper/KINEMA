import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Seat from './Seat';
import api from '../axiosAuth';
import '../styles/SeatPicker.css';
import { jwtDecode } from 'jwt-decode'

const SeatPicker = () => {
  const { screeningId } = useParams();
  const [seats, setSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        console.log('Decoded token:', decodedToken);
        setUserId(decodedToken.userId);
        console.log(userId);
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    }
  }, []);

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

  Object.keys(rows).forEach(row => {
    rows[row].sort((a, b) => a.seat.seatColumn - b.seat.seatColumn);
  });

  const toggleSelectSeat = (seatId) => {
    setSelectedSeats((prevSelectedSeats) => {
      if (prevSelectedSeats.includes(seatId)) {
        return prevSelectedSeats.filter(id => id !== seatId);
      } else {
        return [...prevSelectedSeats, seatId];
      }
    });
  };

  const reserveSeats = async () => {
    try {
      const response = await api.post(`/api/seat-availability/reserve-multiple`, { 
        screeningId: parseInt(screeningId), 
        seatIds: selectedSeats 
      });
      const updatedSeats = response.data;
      setSeats(prevSeats =>
        prevSeats.map(seat => updatedSeats.find(updatedSeat => updatedSeat.id === seat.id) || seat)
      );
      console.log('Seats reserved successfully, now creating booking...');
      await createBooking(); // Call to create booking
    } catch (error) {
      console.error('Error reserving seats:', error);
    }
  };

  const createBooking = async () => {
    if (!userId) {
      console.error('User not logged in or userId not set');
      return;
    }
  
    try {
      const bookingData = {
        userId: userId,
        screeningId: screeningId,
        seatIds: selectedSeats,
        ticketType: 'Regular', // Możesz dostosować to według potrzeb
    };
      console.log('Creating booking with data:', bookingData);
      await api.post(`/api/bookings`, bookingData);
      alert('Booking created successfully!');
      setSelectedSeats([]); // Clear selected seats after booking
    } catch (error) {
      console.error('Error creating booking:', error);
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
                  isAvailable={seat.available}
                  isSelected={selectedSeats.includes(seat.seat.id)}
                  onClick={() => toggleSelectSeat(seat.seat.id)}
                />
              ))}
            </div>
          </div>
        ))}
      </div>
      {selectedSeats.length > 0 && (
        <button onClick={reserveSeats} className="reserve-button">
          Zarezerwuj miejsca
        </button>
      )}
    </div>
  );
};

export default SeatPicker;
