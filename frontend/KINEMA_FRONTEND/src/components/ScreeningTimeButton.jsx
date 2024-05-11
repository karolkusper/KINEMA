import React from 'react'
import "../styles/ScreeningTimeButton.css"

function ScreeningTimeButton({time}) {
  return (
    <div className='screeningTimeButton'>{time}</div>
  )
}

export default ScreeningTimeButton