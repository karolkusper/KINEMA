import React from 'react'
import "../styles/Forms.css"
import {Link} from 'react-router-dom'

function Login() {
  return (
    <div>
        <div className="form-box">
            <form className="login" action="login" method="POST">
                <div className="inputs">
                    <input name="email" type="text" placeholder="email"/>
                    <input name="password" type="password" placeholder="password"/>
                </div>
                <button type="submit" className="button">Log in</button>
            </form>
            <div className="h">
                <h3>Don't have an account?</h3>
                <Link to='/auth/register'><h2>Create an Account</h2></Link>
            </div>
        </div>
</div>
  )
}

export default Login