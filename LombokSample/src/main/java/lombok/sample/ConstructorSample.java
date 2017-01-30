package lombok.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンストラクタ関連サンプル
 */
@SuppressWarnings("javadoc")
@Data
public class ConstructorSample {

    @AllArgsConstructor
    @ToString
    private static class AllArgsSample {
        private int intValue;
        private String stringValue;
    }

    @RequiredArgsConstructor
    @ToString
    private static class RequiredArgsSample {
        private int intValue;
        final private String stringValue;
    }

    @NoArgsConstructor
    @ToString
    private static class NoArgsSample {
        private int intValue;
        private String stringValue;
    }

    public static void main(String[] args) {
        // @AllArgsConstructor
        // 全てのフィールドを指定するコンストラクタが追加される
        AllArgsSample all = new AllArgsSample(123, "abc");
        System.out.println(all);

        // @RequiredArgsConstructor
        // finalフィールドのみを指定するコンストラクタが追加される
        RequiredArgsSample required = new RequiredArgsSample("abc");
        System.out.println(required);

        // @NoArgsConstructor
        // 引数なしのコンストラクタが追加される
        NoArgsSample no = new NoArgsSample();
        System.out.println(no);
    }

}
