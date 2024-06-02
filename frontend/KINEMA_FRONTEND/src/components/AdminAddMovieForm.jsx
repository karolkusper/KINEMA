import React, { useState } from 'react';
import api from "../axiosAuth";
import "../styles/Forms.css";

function AdminAddMovieForm() {
    const [formData, setFormData] = useState({
        title: '',
        desc: '',
        director: '',
        productionYear: '',
        movieGenre: '',
        image: null,
    });

    const handleChange = (e) => {
        const { name, value, files } = e.target;
        if (name === "image") {
            setFormData({ ...formData, image: files[0] });
        } else {
            setFormData({ ...formData, [name]: value });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.title || !formData.desc || !formData.director || !formData.productionYear || !formData.movieGenre || !formData.image) {
            alert("Please fill in all fields.");
            return;
        }

        // Upload image to the frontend server
        const imageData = new FormData();
        imageData.append('image', formData.image);

        try {
            // Upload image to the server
            const imageUploadResponse = await api.post('/api/upload', imageData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            // Get the URL of the uploaded image
            const imageUrl = imageUploadResponse.data.url;

            // Prepare the data to send to backend
            const movieData = {
                title: formData.title,
                description: formData.desc,
                director: formData.director,
                productionYear: formData.productionYear,
                movieGenre: formData.movieGenre,
                posterPath: imageUrl
            };

            // Send movie data to backend
            const response = await api.post('/api/movies', movieData);
            console.log('Adding a movie to db successful:', response.data);
            alert('Movie added successfully!');

            // Clear the form
            setFormData({
                title: '',
                desc: '',
                director: '',
                productionYear: '',
                movieGenre: '',
                image: null,
            });
        } catch (error) {
            console.error('Adding a movie to db failed:', error.response.data);
        }
    };

    return (
        <div className='form_page'>
            <div className="form-box admin">
                <form onSubmit={handleSubmit}>
                    <div className="inputs">
                        <input name="title" type="text" placeholder="Title" value={formData.title} onChange={handleChange} />
                        <textarea name="desc" placeholder="Description" value={formData.desc} onChange={handleChange} />
                        <input name="director" type="text" placeholder="Director" value={formData.director} onChange={handleChange} />
                        <input name="productionYear" type="text" placeholder="Production Year" value={formData.productionYear} onChange={handleChange} />
                        <input name="movieGenre" type="text" placeholder="Genre" value={formData.movieGenre} onChange={handleChange} />
                        <input name="image" type="file" placeholder="Image" onChange={handleChange} />
                    </div>
                    <button className="button" type="submit">Add new movie</button>
                </form>
            </div>
        </div>
    );
}

export default AdminAddMovieForm;
