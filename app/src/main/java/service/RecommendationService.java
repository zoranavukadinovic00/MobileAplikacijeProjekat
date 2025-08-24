package service;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Movie;
import Model.Review;
import db.AppDatabase;

public class RecommendationService {
    private AppDatabase db;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public interface RecommendationCallback {
        void onRecommendationsReady(List<Movie> recommendations);
    }

    public RecommendationService(Context context) {
        this.db = AppDatabase.getInstance(context);
    }

    public void generateRecommendations(long userId, RecommendationCallback callback) {
        executor.execute(() -> {
            try {
                List<Review> userReviews = db.reviewDao().getHighRatedByUser(userId);
                List<Movie> allMovies = db.movieDao().getAll().getValue();

                if (userReviews.isEmpty() || allMovies == null) {
                    callback.onRecommendationsReady(new ArrayList<>());
                    return;
                }

                Map<String, Integer> genreScores = calculateGenrePreferences(userReviews, allMovies);
                List<Movie> recommendations = findSimilarMovies(userReviews, allMovies, genreScores);

                callback.onRecommendationsReady(recommendations);
            } catch (Exception e) {
                callback.onRecommendationsReady(new ArrayList<>());
            }
        });
    }

    private Map<String, Integer> calculateGenrePreferences(List<Review> userReviews, List<Movie> allMovies) {
        Map<String, Integer> genreScores = new HashMap<>();

        for (Review review : userReviews) {
            Movie movie = findMovieById(allMovies, review.getMovieId());
            if (movie != null && movie.getGenre() != null) {
                String genre = movie.getGenre().toLowerCase();
                genreScores.put(genre, genreScores.getOrDefault(genre, 0) + review.getRating());
            }
        }

        return genreScores;
    }

    private List<Movie> findSimilarMovies(List<Review> userReviews, List<Movie> allMovies, Map<String, Integer> genreScores) {
        List<Long> watchedMovieIds = new ArrayList<>();
        for (Review review : userReviews) {
            watchedMovieIds.add(review.getMovieId());
        }

        List<Movie> recommendations = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (!watchedMovieIds.contains(movie.getId()) && movie.getGenre() != null) {
                String movieGenre = movie.getGenre().toLowerCase();
                if (genreScores.containsKey(movieGenre) && genreScores.get(movieGenre) >= 4) {
                    recommendations.add(movie);
                }
            }

            if (recommendations.size() >= 10) break;
        }

        return recommendations;
    }

    private Movie findMovieById(List<Movie> movies, Long movieId) {
        for (Movie movie : movies) {
            if (movie.getId() != null && movie.getId().equals(movieId)) {
                return movie;
            }
        }
        return null;
    }
}