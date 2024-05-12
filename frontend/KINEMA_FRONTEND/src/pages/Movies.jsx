import React, { useState, useEffect } from 'react';
import MovieItem from '../components/MovieItem';
import Slider from '../components/Slider';
import '../styles/Movies.css';
import api from "../axiosAuth";

function Movies() {
    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await api.get('/api/movies');
                setMovies(response.data);
            } catch (error) {
                console.log('Error fetching movies:', error);
            }
        };

        fetchMovies();
    }, []);

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
                        title={movie.title}
                        desc={movie.description}
                        director={movie.director}
                        productionYear={movie.productionYear}
                        // image={movie.image} // Assuming you have an 'image' property in your Movie model
                        image={movie.posterPath}
                    />
                ))}
            </div>
        </div>
    );
}

export default Movies;