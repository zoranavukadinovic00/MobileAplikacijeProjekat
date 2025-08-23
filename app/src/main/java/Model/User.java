package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")          // <-- NOVO
    private String password;                // <-- NOVO


    @ColumnInfo(name = "email")
    private String email; // može biti null

    // Room ne podržava List<String> bez TypeConverter-a
    @ColumnInfo(name = "watched_movies")
    private List<String> watchedMovies;

    @ColumnInfo(name = "wish_list")
    private List<String> wishList;

    public User() { }

    @Ignore
    public User(Long id, String username, String email, List<String> watchedMovies, List<String> wishList,String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.watchedMovies = watchedMovies;
        this.wishList = wishList;
        this.password=password;
    }

    // getters/setters

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<String> getWatchedMovies() { return watchedMovies; }
    public void setWatchedMovies(List<String> watchedMovies) { this.watchedMovies = watchedMovies; }
    public List<String> getWishList() { return wishList; }
    public void setWishList(List<String> wishList) { this.wishList = wishList; }
}
