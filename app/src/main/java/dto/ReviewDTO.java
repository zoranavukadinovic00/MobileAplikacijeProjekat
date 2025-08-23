package dto;

import Model.Review;

public class ReviewDTO {
    public Long id;
    public Long movieId;
    public Long userId;
    public String username;
    public int rating;
    public String comment;
    public long timestamp;

    public static ReviewDTO fromEntity(Review e) {
        ReviewDTO d = new ReviewDTO();
        d.id = e.getId();
        d.movieId = e.getMovieId();
        d.userId = e.getUserId();
        d.username = e.getUsername();
        d.rating = e.getRating();
        d.comment = e.getComment();
        d.timestamp = e.getTimestamp();
        return d;
    }

    public Review toEntity() {
        return new Review(id, movieId, userId, username, rating, comment, timestamp);
    }
}
