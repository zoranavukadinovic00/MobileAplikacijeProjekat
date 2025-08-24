package Model;

<<<<<<< HEAD
import androidx.room.ColumnInfo;
=======
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

<<<<<<< HEAD
@Entity(
        tableName = "reviews",
        foreignKeys = {
                @ForeignKey(
                        entity = Movie.class,
                        parentColumns = "id",
                        childColumns = "movie_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = { @Index("movie_id"), @Index("user_id") }
)
public class Review {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "movie_id")
    private Long movieId;

    @ColumnInfo(name = "user_id")
    private Long userId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "rating")
    private int rating;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "time_stamp")
    private long timestamp;

    public Review() { }

    @Ignore
    public Review(Long id, Movie movie, User user, String username, int rating, String comment, long timestamp) {
        this.id = id;
        this.movieId = movie != null ? movie.getId() : null;
        this.userId = user != null ? user.getId() : null;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    
=======
@Entity(tableName = "reviews",
        foreignKeys = {
                @ForeignKey(entity = Movie.class, parentColumns = "id", childColumns = "movieId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("movieId"), @Index("userId")})
public class Review {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long movieId;
    private Long userId;
    private String username;
    private int rating;
    private String comment;
    private long timestamp;

    public Review() {}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
    @Ignore
    public Review(Long id, Long movieId, Long userId, String username, int rating, String comment, long timestamp) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

<<<<<<< HEAD

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
=======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
