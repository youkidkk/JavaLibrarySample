package lombok.sample;

import lombok.val;

/**
 * val サンプル
 */
@SuppressWarnings("javadoc")
public class ValSample {

    public static void main(String[] args) {
        // val で変数を宣言する
        // 型指定を省略でき、自動的に final 指定される
        val v = "abcdef";

        // abcdef となる
        System.out.println("value : " + v);
        // java.lang.String が表示される
        System.out.println("class : " + v.getClass().getName());

        // final 指定されるため、以下はコンパイルエラーとなる
        // v = "xyz";
    }

}
