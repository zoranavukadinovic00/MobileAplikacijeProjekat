package view;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Review;
import repo.dao.ReviewRepository;

public class ReviewViewModel extends AndroidViewModel {
    private ReviewRepository repository;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        repository = new ReviewRepository(application);
    }

    public LiveData<List<Review>> getReviewsForMovie(long movieId) {
        return repository.getByMovieId(movieId);
    }

    public LiveData<List<Review>> getReviewsByUser(long userId) {
        return repository.getByUserId(userId);
    }

    public LiveData<Double> getAverageRating(long movieId) {
        MutableLiveData<Double> result = new MutableLiveData<>();
        executor.execute(() -> {
            Double avg = repository.getAverageRatingForMovie(movieId);
            result.postValue(avg != null ? avg : 0.0);
        });
        return result;
    }

    public void insertReview(Review review) {
        repository.insert(review);
    }

    public void updateReview(Review review) {
        repository.update(review);
    }

    public void deleteReview(Review review) {
        repository.delete(review);
    }
}