import React, { useState, useEffect } from 'react';
import MovieItem from '../components/MovieItem';
import Slider from '../components/Slider';
import '../styles/Movies.css';
import api from "../axiosAuth";
import { jwtDecode } from 'jwt-decode'
function Movies() {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchMovies = async () => {
            try {

                const token = localStorage.getItem('token');
                if (token) {
                    const decodedToken = jwtDecode(token);
                    console.log('Decoded Token:', decodedToken);

                    // Sprawdź role użytkownika
                    const roles = decodedToken.roles || [];
                    console.log('User Roles:', roles);
                }

                const response = await api.get('/api/movies');
                setMovies(response.data);
            } catch (error) {
                console.log('Error fetching movies:', error);
            }
        };

        fetchMovies();
    }, []);

    const handleDeleteMovie = (id) => {
        setMovies((prevMovies) => prevMovies.filter((movie) => movie.id !== id));
    };

    return (
        <div className='movies'>
            <h1 className='moviesTitle'>Our movies</h1>
            <div className='sliderDiv'>
                <Slider movies={movies} />
            </div>

            <div className='moviesList'>
                {movies.map((movie) => (
                    <MovieItem
                        key={movie.id}
                        id={movie.id}
                        title={movie.title}
                        desc={movie.description}
                        director={movie.director}
                        productionYear={movie.productionYear}
                        // image={movie.image} // Assuming you have an 'image' property in your Movie model
                        image={movie.posterPath}
                        onDelete={handleDeleteMovie}
                    />
                ))}
            </div>
        </div>
    );
}

export default Movies;