package repo.dao;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.AppDatabase;
import Model.Review;
import repo.dao.ReviewDao;

public class ReviewRepository {
    private final ReviewDao reviewDao;
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public ReviewRepository(Context context) {
        reviewDao = AppDatabase.getInstance(context).reviewDao();
    }

    public LiveData<List<Review>> getByMovieId(long movieId) { return reviewDao.getByMovieId(movieId); }

    public LiveData<List<Review>> getByUserId(long userId) { return reviewDao.getByUserId(userId); }

    public LiveData<Double> getAverageRatingForMovie(long movieId) { return reviewDao.getAverageRatingForMovie(movieId); }

    public void insert(Review review) { io.execute(() -> reviewDao.insert(review)); }

    public void update(Review review) { io.execute(() -> reviewDao.update(review)); }

    public void delete(Review review) { io.execute(() -> reviewDao.delete(review)); }
}
