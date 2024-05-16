package com.karolkusper.KINEMA.entity;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name="movie_genre")
    private String movieGenre;

    @Column(name="poster_path")
    private String posterPath;

    public Movie(){}

    public Movie(String title, String description, String director, int productionYear, String movieGenre, String posterPath) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.productionYear = productionYear;
        this.movieGenre = movieGenre;
        this.posterPath = posterPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", productionYear=" + productionYear +
                ", movieGenre='" + movieGenre + '\'' +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }
}
