package repo.dao;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.AppDatabase;
import Model.Movie;
import repo.dao.MovieDao;

public class MovieRepository {
    private final MovieDao movieDao;
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public MovieRepository(Context context) {
        movieDao = AppDatabase.getInstance(context).movieDao();
    }

    public LiveData<List<Movie>> getAll() { return movieDao.getAll(); }

    public LiveData<Movie> getById(long id) { return movieDao.getById(id); }

    public LiveData<List<Movie>> searchByTitle(String q) { return movieDao.searchByTitle(q); }

    public void insert(Movie movie) { io.execute(() -> movieDao.insert(movie)); }

    public void update(Movie movie) { io.execute(() -> movieDao.update(movie)); }

    public void delete(Movie movie) { io.execute(() -> movieDao.delete(movie)); }
}
