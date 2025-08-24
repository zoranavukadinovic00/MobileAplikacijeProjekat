package repo.dao;

<<<<<<< HEAD
=======
import androidx.lifecycle.LiveData;
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
import Model.User;

@Dao
public interface UserDao {
<<<<<<< HEAD

=======
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
    @Insert
    long insert(User user);

    @Update
    int update(User user);

    @Delete
    int delete(User user);

<<<<<<< HEAD
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User getById(long id);
=======
    @Query("SELECT * FROM users ORDER BY username ASC")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    LiveData<User> getById(long id);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User getByIdSingle(long id);
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getByUsername(String username);

<<<<<<< HEAD
    // Login bez email-a
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User getByUsernameAndPassword(String username, String password);

    // Provere za registraciju
=======
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User getByUsernameAndPassword(String username, String password);

>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    int countByUsername(String username);

    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    int countByEmail(String email);
<<<<<<< HEAD
}
=======
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
