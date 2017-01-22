package spark;

import static spark.Spark.*;

/**
 * SparkFrameworkサンプルクラス
 */
public class SparkFrameworkSample {

    /**
     * メイン
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        port(80);

        get("/hello", (req, res) -> "Hello World");
    }

}