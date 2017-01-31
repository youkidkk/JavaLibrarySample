package guava.sample;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Joiner サンプル
 */
@SuppressWarnings("javadoc")
public class JoinerSample {

    public static void main(String[] args) {
        System.out.println(Joiner.on(", ")
                .join("value A", "value B", "value C"));
        // →「value A, value B, value C」

        List<String> strings = Lists.newArrayList("value A", null, "value B", null, "value C");

        System.out.println(Joiner.on(", ")
                .skipNulls() // null をスキップする
                .join(strings));
        // →「value A, value B, value C」

        System.out.println(Joiner.on(", ")
                .useForNull("Null Value!") // null の場合に引数の値で置き換える
                .join(strings));
        // →「value A, Null Value!, value B, Null Value!, value C」
    }

}
