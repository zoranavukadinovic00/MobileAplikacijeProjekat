package Model;

<<<<<<< HEAD
import androidx.room.ColumnInfo;
=======
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
<<<<<<< HEAD

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    public Movie() { }

=======
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String genre;
    private int year;
    private double rating;
    private String description;
    private String imageUrl;

    public Movie() {}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
    @Ignore
    public Movie(Long id, String title, String genre, int year, double rating, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.description = description;
        this.imageUrl = imageUrl;
    }

<<<<<<< HEAD

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
=======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
