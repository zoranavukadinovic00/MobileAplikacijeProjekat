package repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import Model.Review;

@Dao
public interface ReviewDao {
    @Insert
    long insert(Review review);

    @Update
    int update(Review review);

    @Delete
    int delete(Review review);

    @Query("SELECT * FROM reviews WHERE movie_id = :movieId ORDER BY time_stamp DESC")
    LiveData<List<Review>> getByMovieId(long movieId);

    @Query("SELECT * FROM reviews WHERE user_id = :userId ORDER BY time_stamp DESC")
    LiveData<List<Review>> getByUserId(long userId);

    @Query("SELECT AVG(rating) FROM reviews WHERE movie_id = :movieId")
    LiveData<Double> getAverageRatingForMovie(long movieId);
}
