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
        version = 2,                 // ↑ podigni kad promeniš šemu (npr. dodao password)
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
                            // U DEV fazi: spreči crash kad menjaš šemu
                            .fallbackToDestructiveMigration()
                            // opciono: brži upis
                            // .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
