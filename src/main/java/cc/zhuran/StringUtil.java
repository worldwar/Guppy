package cc.zhuran;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class StringUtil {
    public static String urlFor(String title) {
        return Joiner.on('-').join(Splitter.onPattern(" |`").split(title));
    }

    public static String urlWithDateFor(String title, String date) {
        return Joiner.on('-').join(date, urlFor(title));
    }
}
