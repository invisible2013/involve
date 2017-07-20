package ge.economy.involve.utils;

import com.google.gson.Gson;

/**
 * Created by nino on 7/21/16.
 */
public class GsonUtil {

    public static <T> T fromJson(String json, Class<T> c) {
        return new Gson().fromJson(json, c);
    }

    public static String toJson(Object c) {
        return new Gson().toJson(c);
    }

}
