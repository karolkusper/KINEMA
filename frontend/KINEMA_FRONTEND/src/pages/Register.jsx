import React from 'react'
import "../styles/Forms.css"
import {Link} from 'react-router-dom'
import { useState } from 'react';
import axiosGetAuth from '../axiosGetAuth.jsx'

function Register() {

    const [formData,setFormData] = useState({
        'userName':'',
        'email':'',
        'firstName':'',
        'lastName':'',
        'password':'',
        'confirmed':'',
    })

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setFormData({...formData,[name]:value});
    };


    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const response = await axiosGetAuth.post('/api/v1/auth/register',formData);
            console.log('Registration successful:', response.data);
        }
        catch (error){
            console.error('Registration failed:',error.response.data)
        }
    }

  return (
    <div className='form_page'>
        <div className="form-box">
        <form onSubmit={handleSubmit}>
            <div className="inputs">
                <input name="userName" type="text" placeholder="Username" value={formData.userName} onChange={handleChange} />
                <input name="email" type="email" placeholder="Email" value={formData.email} onChange={handleChange}/>
                <input name="firstName" type="text" placeholder="Firstname" value={formData.firstName} onChange={handleChange}/>
                <input name="lastName" type="text" placeholder="Lastname" value={formData.lastName} onChange={handleChange}/>
                <input name="password" type="password" placeholder="Password" value={formData.password} onChange={handleChange}/>
                <input name="confirmed" type="password" placeholder="Confirm Password" value={formData.confirmed} onChange={handleChange}/>
            </div>
            <button className="button" type="submit">Register</button>
        </form>


            <div className="h">
                <h3>Already have an account?</h3>
                <Link to='/'><h2>Login Now</h2></Link>
            </div>
        </div>
    </div>
  )
}

export default Register