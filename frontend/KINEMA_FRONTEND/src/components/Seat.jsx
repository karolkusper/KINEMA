import React from 'react';
import '../styles/Seat.css';

const Seat = ({ row, column, isAvailable, isSelected, onClick }) => {
  return (
    <div
      className={`seat ${isAvailable ? 'available' : 'unavailable'} ${isSelected ? 'selected' : ''}`}
      onClick={isAvailable ? onClick : null}
    >
      {column}
    </div>
  );
};

export default Seat;
