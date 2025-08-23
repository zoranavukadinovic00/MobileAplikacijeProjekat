package util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
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
