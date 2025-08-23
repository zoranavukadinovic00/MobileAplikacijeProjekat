package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import Model.Movie;
import Model.Review;
import Model.Suggestion;
import Model.User;
import repo.dao.MovieDao;
import repo.dao.ReviewDao;
import repo.dao.SuggestionDao;
import repo.dao.UserDao;

@Database(
        entities = { Movie.class, Review.class, Suggestion.class, User.class },
        version = 2,
        exportSchema = false
)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract MovieDao movieDao();
    public abstract ReviewDao reviewDao();
    public abstract SuggestionDao suggestionDao();
    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "projekat_rma.db"
                            )

                            .fallbackToDestructiveMigration()

                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
