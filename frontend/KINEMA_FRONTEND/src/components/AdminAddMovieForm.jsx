import React from 'react'
import api from "../axiosAuth";
import { useState } from 'react';
import "../styles/Forms.css";

function AdminAddMovieForm() {
    const [formData,setFormData] = useState({
        'title':'',
        'desc':'',
        'director':'',
        'productionYear':'',
        'movieGenre':'',
        'image':'',
    })

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setFormData[{...formData,[name]:value}];
    }

    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const response = await api.post('/api/movies',formData);
            console.log('Adding a movie do db successful:', response.data);
        }
        catch (error){
            console.error('Adding a movie do db  failed:',error.response.data)
        }
    }

  return (

    <div className='form_page'>
        <div className="form-box admin">
            <form onSubmit={handleSubmit}>
                <div className="inputs">
                    <input name="title" type="text" placeholder="Title" value={formData.title} onChange={handleChange} />
                    <input name="desc" type="text"  placeholder="Description" value={formData.desc} onChange={handleChange}/>
                    <input name="director" type="text"  placeholder="Director" value={formData.director} onChange={handleChange}/>
                    <input name="productionYear" type="text" placeholder="Production Year" value={formData.productionYear} onChange={handleChange}/>
                    <input name="movieGenre" type="text" placeholder="Genre" value={formData.movieGenre} onChange={handleChange}/>
                    <input name="image" type="text" placeholder="Image path" value={formData.image} onChange={handleChange}/>
                </div>
                <button className="button" type="submit">Add new movie</button>
            </form>
        </div>
    </div>
  )
}

export default AdminAddMovieForm