package dto;

import Model.Movie;

public class MovieDTO {
    public Long id;
    public String title;
    public String genre;
    public int year;
    public double rating;
    public String description;
    public String imageUrl;

    public static MovieDTO fromEntity(Movie e) {
        MovieDTO d = new MovieDTO();
        d.id = e.getId();
        d.title = e.getTitle();
        d.genre = e.getGenre();
        d.year = e.getYear();
        d.rating = e.getRating();
        d.description = e.getDescription();
        d.imageUrl = e.getImageUrl();
        return d;
    }

    public Movie toEntity() {
        return new Movie(id, title, genre, year, rating, description, imageUrl);
    }
}
