import React from 'react'
import defaultProfilePicture from '../assets/defaultProfilePicture.png'
import "../styles/Profile.css"
// import api from "../axiosAuth"
// import { useState ,useEffect} from 'react';

function Profile() {

  //   const [user, setUser] = useState(null);

  // useEffect(() => {
  //   const fetchProfile = async () => {
  //     try {
  //       const response = await api.get('/api/users');
  //       setUser(response.data);
  //       console.log(response.data);
  //       console.log(localStorage.getItem('token').data)
  //     } catch (error) {
  //       // Handle error or redirect to login
  //       console.log(localStorage.getItem('token').data)
  //     }
  //   };

  //   fetchProfile();
  // }, []);

  // if (!user) {
  //   return <div>Loading...</div>;
  // }
  return (
    <div className="container">
      {/* <img
              src="public/img/myProfile.jpg"
              alt="obrazek_w_tle"
              className="backgroundImg"
      /> */}
     
      <div id="Title"><h1 id="title">Witaj! Oto Twój profil:</h1></div>

      <div className="profile">
          <div className="content">
              <img className="profilePic"
                  src={defaultProfilePicture}
                  alt="zdj profilowe"/>

              {/* <div id="changeProfilePicture">
                  <button type="button" id="changeProfilePicture" className="profileButton">Zmień zdjęcie profilowe</button>
              </div> */}

              <div className="user_data">
                  <h2>Username:</h2>
                  <h2>Email: </h2>
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