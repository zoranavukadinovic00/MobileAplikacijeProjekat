package view;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import Model.Movie;
import repo.dao.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAll();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public LiveData<Movie> getMovieById(long id) {
        return repository.getById(id);
    }

    public LiveData<List<Movie>> searchMovies(String query) {
        return repository.searchByTitle(query);
    }

    public void insert(Movie movie) {
        repository.insert(movie);
    }

    public void update(Movie movie) {
        repository.update(movie);
    }

    public void delete(Movie movie) {
        repository.delete(movie);
    }
}