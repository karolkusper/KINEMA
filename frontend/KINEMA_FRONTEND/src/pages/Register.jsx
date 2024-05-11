import React from 'react'
import "../styles/Forms.css"

function Register() {
  return (
    <div>
        <div className="form-box">
            <form action="register" method="POST">
                <div class="inputs">
                    <input name="username" type="text" placeholder="Username" />
                    <input name="email" type="email" placeholder="Email" />
                    <input name="password" type="password" placeholder="Password" />
                    <input name="confirmed" type="password" placeholder="Confirm Password" />
                </div>
                <button className="button" type="submit">Register</button>
            </form>

            <div className="h">
                <h3>Already have an account?</h3>
                <a href="index"><h2>Login Now</h2></a>
            </div>
        </div>
    </div>
  )
}

export default Register