import React from 'react'
import defaultProfilePicture from '../assets/defaultProfilePicture.png'
import { jwtDecode } from 'jwt-decode'
import "../styles/Profile.css"
import api from "../axiosAuth"
import { useState ,useEffect} from 'react';

function Profile() {

    const [user, setUser] = useState(null);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
      const fetchProfile = async () => {
        try {
          const token = localStorage.getItem('token');
          if (!token) {
            console.log("Token not found");
            return;
          }
  
            // Odczytaj userId z tokenu JWT
          const decodedToken = jwtDecode(token);
          console.log(decodedToken)
          const userId = decodedToken.userId;
          setUserId(userId);

          console.log(`/api/users/${userId}`)
          const response = await api.get(`/api/users/${userId}`);
          
          setUser(response.data);
          console.log(response.data);
        } catch (error) {
          console.log("Error fetching user data:", error);
          // Handle error or redirect to login
        }
      };
  
      fetchProfile();
    }, []);

  if (!user) {
    return <div>Loading...</div>;
  }
  return (
    <div className="container">
      {/* <img
              src="public/img/myProfile.jpg"
              alt="obrazek_w_tle"
              className="backgroundImg"
      /> */}
     
      <div id="Title"><h1 id="title">Witaj {user.firstName}! Oto Twój profil:</h1></div>

      <div className="profile">
          <div className="content">
              <img className="profilePic"
                  src={defaultProfilePicture}
                  alt="zdj profilowe"/>

              {/* <div id="changeProfilePicture">
                  <button type="button" id="changeProfilePicture" className="profileButton">Zmień zdjęcie profilowe</button>
              </div> */}

              <div className="user_data">
                  <h2>Username: {user.userName}</h2>
                  <h2>Firstname: {user.firstName}</h2>
                  <h2>Lastname: {user.lastName}</h2>
                  <h2>Email: {user.email}</h2>
                  <h2>Role: {user.roles.map(role => role.name).join(', ')}</h2>
              </div>

              {/* <div id="changeProfileData">
                  <button type="button" className="profileButton">Zmień dane profilu</button>
              </div> */}
        </div>
      </div>
    </div>
  )
}

export default Profile