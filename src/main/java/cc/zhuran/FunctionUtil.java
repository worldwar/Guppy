package cc.zhuran;

import com.google.common.base.Function;
import com.google.common.base.Functions;

import java.util.HashMap;
import java.util.Map;

public class FunctionUtil {
    private static Map<Integer, String> numbers = new HashMap<>();

    static {
        numbers.put(5, "five");
        numbers.put(6, "six");
    }

    public static String numberToEnglish(int i) {
        return Functions.forMap(numbers).apply(i);
    }

    public static String numberToChar(int i) {
        return Functions.compose(new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input.substring(0, 1).toUpperCase();
            }
        }, Functions.forMap(numbers)
        ).apply(i);
    }
}
