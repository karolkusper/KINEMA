import React from 'react';
import '../styles/Seat.css';

const Seat = ({ column, isAvailable, onClick }) => {
  return (
    <div 
      className={`seat ${isAvailable ? 'available' : 'reserved'}`} 
      onClick={isAvailable ? onClick : null}
    >
      {column}
    </div>
  );
};

export default Seat;
