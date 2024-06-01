import React, { useState, useEffect } from 'react';
import api from "../axiosAuth";
import "../styles/Forms.css";

function AdminAddScreeningForm() {
    const [formData, setFormData] = useState({
        cinemaHall: '',
        movie: '',
        startTime: '',
        endTime: '',
        format: '',
    });

    const [cinemaHalls, setCinemaHalls] = useState([]);
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchCinemaHalls = async () => {
            try {
                const response = await api.get('/api/cinemahall');
                setCinemaHalls(response.data);
            } catch (error) {
                console.error('Error fetching cinema halls:', error);
            }
        };

        const fetchMovies = async () => {
            try {
                const response = await api.get('/api/movies');
                setMovies(response.data);
            } catch (error) {
                console.error('Error fetching movies:', error);
            }
        };

        fetchCinemaHalls();
        fetchMovies();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log('Form data before submit:', formData); // Dodaj to, aby sprawdzić, jakie dane są wysyłane
            const response = await api.post('/api/screenings', formData);
            alert('New screening added successfully!');
            console.log('Adding a screening do db successful:', response.data);
        } catch (error) {
            console.error('Adding a screening do db failed:', error.response.data);
        }
    };

    return (
        <div className='form_page'>
            <div className="form-box admin">
                <form onSubmit={handleSubmit}>
                    <div className="inputs">
                        <select name="cinemaHall" value={formData.cinemaHall} onChange={handleChange}>
                            <option value="">Select Hall</option>
                            {cinemaHalls.map((hall) => (
                                <option key={hall.id} value={hall.id}>{hall.hallName}</option>
                            ))}
                        </select>
                        <select name="movie" value={formData.movie} onChange={handleChange}>
                            <option value="">Select Movie</option>
                            {movies.map((movie) => (
                                <option key={movie.id} value={movie.id}>{movie.title}</option>
                            ))}
                        </select>
                        <input name="startTime" type="datetime-local" placeholder="Start time" value={formData.startTime} onChange={handleChange} />
                        <input name="endTime" type="datetime-local" placeholder="End time" value={formData.endTime} onChange={handleChange} />
                        <select name="format" value={formData.format} onChange={handleChange}>
                            <option value="">Select Format</option>
                            <option value="2D">2D</option>
                            <option value="3D">3D</option>
                        </select>
                    </div>
                    <button className="button" type="submit">Add new screening</button>
                </form>
            </div>
        </div>
    );
}

export default AdminAddScreeningForm;
