package repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import Model.Movie;

@Dao
public interface MovieDao {
    @Insert
    long insert(Movie movie);

    @Update
    int update(Movie movie);

    @Delete
    int delete(Movie movie);

    @Query("SELECT * FROM movies ORDER BY title ASC")
    LiveData<List<Movie>> getAll();

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    LiveData<Movie> getById(long id);

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :q || '%' ORDER BY year DESC")
    LiveData<List<Movie>> searchByTitle(String q);
}
