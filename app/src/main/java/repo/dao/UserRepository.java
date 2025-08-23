package repo.dao;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.AppDatabase;
import Model.User;

public class UserRepository {
    private final UserDao userDao;
    private final ExecutorService io = Executors.newSingleThreadExecutor();

    public UserRepository(Context context) {
        userDao = AppDatabase.getInstance(context).userDao();
    }


    public interface UserCallback {
        void onResult(User user);
    }

    public void getById(long id, UserCallback callback) {
        io.execute(() -> {
            User user = userDao.getById(id);
            if (callback != null) callback.onResult(user);
        });
    }

    public void getByUsername(String username, UserCallback callback) {
        io.execute(() -> {
            User user = userDao.getByUsername(username);
            if (callback != null) callback.onResult(user);
        });
    }


    public void insert(User user) { io.execute(() -> userDao.insert(user)); }

    public void update(User user) { io.execute(() -> userDao.update(user)); }

    public void delete(User user) { io.execute(() -> userDao.delete(user)); }
}
