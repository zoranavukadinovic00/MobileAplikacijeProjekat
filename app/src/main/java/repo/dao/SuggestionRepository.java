package repo.dao;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.AppDatabase;
import Model.Suggestion;
import repo.dao.SuggestionDao;

public class SuggestionRepository {
    private final SuggestionDao suggestionDao;
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public SuggestionRepository(Context context) {
        suggestionDao = AppDatabase.getInstance(context).suggestionDao();
    }

    public LiveData<List<Suggestion>> getByUserId(long userId) { return suggestionDao.getByUserId(userId); }

    public LiveData<List<Suggestion>> getAll() { return suggestionDao.getAll(); }

    public void insert(Suggestion suggestion) { io.execute(() -> suggestionDao.insert(suggestion)); }

    public void update(Suggestion suggestion) { io.execute(() -> suggestionDao.update(suggestion)); }

    public void delete(Suggestion suggestion) { io.execute(() -> suggestionDao.delete(suggestion)); }
}
