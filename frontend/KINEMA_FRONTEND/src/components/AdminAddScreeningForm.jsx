import React from 'react'
import api from "../axiosAuth";
import { useState } from 'react';
import "../styles/Forms.css";

function AdminAddScreeningForm() {
    const [formData,setFormData] = useState({
        'cinemaHall':'',
        'movie':'',
        'startTime':'',
        'endTime':'',
        'format':'',
    })

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setFormData[{...formData,[name]:value}];
    }

    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const response = await api.post('/api/screenings',formData);
            alert('New screening added successfully!');
            console.log('Adding a screening do db successful:', response.data);
        }
        catch (error){
            console.error('Adding a screening do db  failed:',error.response.data)
        }
    }

  return (

    <div className='form_page'>
        <div className="form-box admin">
            <form onSubmit={handleSubmit}>
                <div className="inputs">
                    <input name="cinemaHall" type="text" placeholder="Hall" value={formData.cinemaHall} onChange={handleChange} />
                    <input name="movie" type="text"  placeholder="Movie" value={formData.movie} onChange={handleChange}/>
                    <input name="startTime" type="text"  placeholder="Start time" value={formData.startTime} onChange={handleChange}/>
                    <input name="endTime" type="text" placeholder="End time" value={formData.endTime} onChange={handleChange}/>
                    <input name="format" type="text" placeholder="Format" value={formData.format} onChange={handleChange}/>
                </div>
                <button className="button" onClick={handleSubmit}>Add new screening</button>
            </form>
        </div>
    </div>
  )
}

export default AdminAddScreeningForm