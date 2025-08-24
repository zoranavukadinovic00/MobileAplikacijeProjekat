package util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
<<<<<<< HEAD
    private static final String PREF = "session";
    private static final String KEY_USER_ID = "user_id";

    private final SharedPreferences sp;

    public SessionManager(Context ctx) {
        sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void saveUserId(long id) {
        sp.edit().putLong(KEY_USER_ID, id).apply();
    }

    public long getUserId() {
        return sp.getLong(KEY_USER_ID, -1L);
    }

    public void clear() {
        sp.edit().remove(KEY_USER_ID).apply();
    }
}
=======
    private static final String PREF_NAME = "MovieAppSession";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUserId(Long userId) {
        editor.putLong(KEY_USER_ID, userId);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    public Long getUserId() {
        if (!isLoggedIn()) return null;
        return prefs.getLong(KEY_USER_ID, -1);
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
>>>>>>> d8badaf (Initial commit - MobileAplikacijeProjekat)
