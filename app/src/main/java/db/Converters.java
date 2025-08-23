package db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {
    private static final Gson gson = new Gson();
    private static final Type LIST_STRING_TYPE = new TypeToken<List<String>>(){}.getType();

    @TypeConverter
    public static String fromList(List<String> list) {
        return list == null ? null : gson.toJson(list, LIST_STRING_TYPE);
    }

    @TypeConverter
    public static List<String> toList(String data) {
        if (data == null) return null;
        List<String> list = gson.fromJson(data, LIST_STRING_TYPE);
        return list != null ? list : Collections.emptyList();
    }
}
