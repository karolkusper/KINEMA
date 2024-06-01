import React from 'react'
import defaultProfilePicture from '../assets/defaultProfilePicture.png'
import { jwtDecode } from 'jwt-decode'
import "../styles/Profile.css"
import api from "../axiosAuth"
import { useState ,useEffect} from 'react';

function Profile() {

    const [user, setUser] = useState(null);
    const [userId, setUserId] = useState(null);
    const [bookings, setBookings] = useState([]);

    const formatTime = (time)=>{
      return new Date(time).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    }

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


            // Fetch bookings for the user
        const bookingsResponse = await api.get(`/api/bookings/${userId}`);
        setBookings(bookingsResponse.data);
        console.log(bookingsResponse.data);


        } catch (error) {
          console.log("Error fetching user data:", error);
          // Handle error or redirect to login
        }
      };
  
      fetchProfile();
    }, []);

    const deleteBooking = async (bookingId) => {
      try {
        const deleteResponse = await api.delete(`/api/bookings/${bookingId}`);
        console.log(deleteResponse.data);
        setBookings((prevBookings) => prevBookings.filter((booking) => booking.id !== bookingId));
        alert('Booking deleted successfully!');
      } catch (error) {
        console.error('Error deleting booking:', error);
      }
    };

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
        <div className='my-bookings'>
          <h2>My reservations</h2>
          {bookings.length > 0 ? (
            bookings.map((booking,index) => (
              <div key={booking.id} className='booking-row'>
                <h3>Reservation {index+1}</h3>
                <p>Screening: {booking.screening.movie.title}</p>
                <p>Date: {formatTime(booking.screening.startTime)}</p>
                <p>Hall: {booking.screening.cinemaHall.hallName}</p>
                <p>Seats: {booking.seats.map(seat => `R:${seat.seatRow} C:${seat.seatColumn}`).join(' | ')}</p>
                <p>Ticket type: {booking.ticketType}</p>
                <button onClick={() => deleteBooking(booking.id)} className="delete-button">
                  Delete reservation
                </button>
              </div>
            ))
          ) : (
            <p>Brak rezerwacji</p>
          )}
        </div>
      </div>
    </div>
  )
}

export default Profile