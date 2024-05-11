import React from 'react'
import { useState } from 'react';
import {MovieList} from '../variables/MovieList'
import "../styles/Slider.css"

function Slider() {
    const [currentIndex,setCurrentIndex] = useState(0);
    const goToPrevious = ()=>{
        const isFirstSlide = currentIndex===0;
        const newIndex = isFirstSlide ? MovieList.length-1 : currentIndex-1;
        setCurrentIndex(newIndex);
    }

    const goToNext = ()=>{
        const isLastSlide = currentIndex===MovieList.length-1;
        const newIndex = isLastSlide ? 0 : currentIndex+1;
        setCurrentIndex(newIndex);
    }

    const goToSlide = (index)=>{
        setCurrentIndex(index);
    }
  return (
    <div className='sliderStyles'>
        <div className='arrow left' onClick={goToPrevious}>❰</div>
        <div className='arrow right' onClick={goToNext}>❱</div>

        <div className='slideStyles' style={{backgroundImage: `url(${MovieList[currentIndex].image})`}}></div>

        <div className='dotsContainer'>
            {MovieList.map((movie,key)=>{
               return <div className='dots' key={key} onClick={()=>goToSlide(key)}>●</div>
            })}
        </div>
    </div>
  )
}

export default Slider