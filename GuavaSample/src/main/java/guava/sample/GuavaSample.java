package guava.sample;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Guava サンプル
 */
public class GuavaSample {

    /** ロガー */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * プリミティブ型クラスサンプル<br>
     * Bytes, Longs 等も同様
     */
    public void primitivesSample() {
        // 可変長引数で作成
        List<Integer> intList1 = Ints.asList(1, 2, 3);
        this.logger.info(Joiner.on(", ").join(intList1));

        // 配列から作成
        List<Integer> intList2 = Ints.asList(new int[] { 1, 2, 3 });
        this.logger.info(Joiner.on(", ").join(intList2));

        // Arrays.asList と同様に以下は不可
        // intList1.add(4); // Throw java.lang.UnsupportedOperationException
    }

    /**
     * Lists サンプル
     */
    public void listsSample() {
        // リスト作成
        List<String> stringList = Lists.newArrayList("abc", "def", "ghi");
        stringList.add("jkl"); // OK
        this.logger.info(Joiner.on(", ").skipNulls().join(stringList));
    }

    /**
     * ImmutableList サンプル
     */
    public void immutableListSample() {
        // 変更不可のリスト作成
        List<String> stringImmutableList = ImmutableList.of("abc", "def", "ghi");
        this.logger.info(Joiner.on(", ")
                .join(stringImmutableList));

        // 変更不可のため以下は不可
        // stringImmutableList.add("jkl"); // Throw java.lang.UnsupportedOperationException
    }

    /**
     * メインメソッド
     * @param args 実行時引数
     */
    public static void main(String[] args) {
        GuavaSample sample = new GuavaSample();
        sample.primitivesSample();
        sample.listsSample();
        sample.immutableListSample();
    }

}
