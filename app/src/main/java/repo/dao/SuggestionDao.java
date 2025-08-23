package repo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import Model.Suggestion;

@Dao
public interface SuggestionDao {
    @Insert
    long insert(Suggestion suggestion);

    @Update
    int update(Suggestion suggestion);

    @Delete
    int delete(Suggestion suggestion);

    @Query("SELECT * FROM suggestions WHERE user_id = :userId ORDER BY time_stamp DESC")
    LiveData<List<Suggestion>> getByUserId(long userId);

    @Query("SELECT * FROM suggestions ORDER BY time_stamp DESC")
    LiveData<List<Suggestion>> getAll();
}
