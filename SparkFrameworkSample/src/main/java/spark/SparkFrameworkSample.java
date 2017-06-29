package spark;

import static spark.Spark.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import resteasy.sample.TestResponseType;

import java.util.ArrayList;
import java.util.List;

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

        get("/hello", (req, res) -> {
            res.type("application/json");
            // return "{\"number\": \"1\", \"text\": \"test\"}";
            // return new ObjectMapper().writeValueAsString(new
            // TestResponseType(1, "test"));
            List<TestResponseType> resObj = new ArrayList<>();
            resObj.add(new TestResponseType(1, "test1"));
            resObj.add(new TestResponseType(2, "test2"));
            resObj.add(new TestResponseType(3, "test3"));
            return new ObjectMapper().writeValueAsString(resObj);
        });
    }

}