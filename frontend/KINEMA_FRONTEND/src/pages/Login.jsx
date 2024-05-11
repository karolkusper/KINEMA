import React from 'react'
import "../styles/Forms.css"
import {Link} from 'react-router-dom'
import { useState ,useHistory} from 'react';
import axiosGetAuth from '../axiosGetAuth.jsx'

function Login() {

    const [formData,setFormData] = useState({
        'email':'',
        'password':'',
    })

    const handleChange = (e)=>{
        const {name,value} = e.target;
        setFormData({...formData,[name]:value});
    };

    const history = useHistory();

    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const response = await axiosGetAuth.post('/api/v1/auth/authenticate',formData);
            console.log('Autjentication successful:', response.data);

            // const { token, refreshToken } = response.data;
            const token = response.data;

            // Store the tokens in localStorage
            localStorage.setItem('token', token);
            // localStorage.setItem('refreshToken', refreshToken);

            // Redirect to /content/home
            history.push('/content/home');
            
        }
        catch (error){
            console.error('Autjentication failed:',error.response.data)
        }
    }

  return (
    <div className='form_page'>
        <div className="form-box">
            <form className="login" onSubmit={handleSubmit}>
                <div className="inputs">
                    <input name="email" type="text" placeholder="email" value={formData.email} onChange={handleChange}/>
                    <input name="password" type="password" placeholder="password" value={formData.password} onChange={handleChange}/>
                </div>
                <button type="submit" className="button">Log in</button>
            </form>
            <div className="h">
                <h3>Don't have an account?</h3>
                <Link to='/register'><h2>Create an Account</h2></Link>
            </div>
        </div>
</div>
  )
}

export default Login