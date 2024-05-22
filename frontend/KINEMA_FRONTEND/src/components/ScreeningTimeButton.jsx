import React from 'react'
import { useNavigate } from 'react-router-dom'
import "../styles/ScreeningTimeButton.css"

function ScreeningTimeButton({time, screeningId}) {

  const navigate = useNavigate();

  const handleClick = ()=>{
    navigate(`/content/seats/${screeningId}`);
  };

  return (
    <button className='screeningTimeButton' onClick={handleClick}>{time}</button>
  );
}

export default ScreeningTimeButton