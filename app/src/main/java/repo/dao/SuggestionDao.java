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
import Model.Suggestion;

@Dao
public interface SuggestionDao {
    @Insert
    long insert(Suggestion suggestion);

    @Update
    int update(Suggestion suggestion);

    @Delete
    int delete(Suggestion suggestion);

<<<<<<< HEAD
    @Query("SELECT * FROM suggestions WHERE user_id = :userId ORDER BY time_stamp DESC")
    LiveData<List<Suggestion>> getByUserId(long userId);

    @Query("SELECT * FROM suggestions ORDER BY time_stamp DESC")
    LiveData<List<Suggestion>> getAll();
}
=======
    @Query("SELECT * FROM suggestions ORDER BY timestamp DESC")
    LiveData<List<Suggestion>> getAll();

    @Query("SELECT * FROM suggestions WHERE id = :id LIMIT 1")
    LiveData<Suggestion> getById(long id);

    @Query("SELECT * FROM suggestions WHERE userId = :userId ORDER BY timestamp DESC")
    LiveData<List<Suggestion>> getByUserId(long userId);

    @Query("DELETE FROM suggestions WHERE userId = :userId")
    void deleteByUserId(long userId);
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
