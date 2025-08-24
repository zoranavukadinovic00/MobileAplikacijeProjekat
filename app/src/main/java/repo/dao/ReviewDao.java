package repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
import Model.Review;

@Dao
public interface ReviewDao {
    @Insert
    long insert(Review review);

    @Update
    int update(Review review);

    @Delete
    int delete(Review review);

<<<<<<< HEAD
    @Query("SELECT * FROM reviews WHERE movie_id = :movieId ORDER BY time_stamp DESC")
    LiveData<List<Review>> getByMovieId(long movieId);

    @Query("SELECT * FROM reviews WHERE user_id = :userId ORDER BY time_stamp DESC")
    LiveData<List<Review>> getByUserId(long userId);

    @Query("SELECT AVG(rating) FROM reviews WHERE movie_id = :movieId")
    LiveData<Double> getAverageRatingForMovie(long movieId);
}
=======
    @Query("SELECT * FROM reviews ORDER BY timestamp DESC")
    LiveData<List<Review>> getAll();

    @Query("SELECT * FROM reviews WHERE id = :id LIMIT 1")
    LiveData<Review> getById(long id);

    @Query("SELECT * FROM reviews WHERE movieId = :movieId ORDER BY timestamp DESC")
    LiveData<List<Review>> getByMovieId(long movieId);

    @Query("SELECT * FROM reviews WHERE userId = :userId ORDER BY timestamp DESC")
    LiveData<List<Review>> getByUserId(long userId);

    @Query("SELECT * FROM reviews WHERE userId = :userId AND movieId = :movieId LIMIT 1")
    Review getUserReviewForMovie(long userId, long movieId);

    @Query("SELECT AVG(rating) FROM reviews WHERE movieId = :movieId")
    Double getAverageRatingForMovie(long movieId);

    @Query("SELECT * FROM reviews WHERE userId = :userId AND rating >= 4 ORDER BY timestamp DESC")
    List<Review> getHighRatedByUser(long userId);
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
