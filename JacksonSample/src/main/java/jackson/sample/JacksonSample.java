package jackson.sample;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Jacksonサンプル
 */
public class JacksonSample {

    /**
     * メイン
     * @param args コマンドライン引数
     * @throws JsonParseException JSON解析例外時
     * @throws JsonMappingException JSONマッピング例外時
     * @throws IOException IO例外時
     */
    public static void main(String[] args)
            throws JsonParseException, JsonMappingException, IOException {
        String json = "{\"name\" : \"Nobunaga\",\"email\" : \"nobunaga@gmail.com\",\"friends\" : [{\"name\" : \"Hideyoshi\",\"email\" : \"hideyoshi@gmail.com\"}, {\"name\" : \"Ieyasu\",\"email\" : \"ieyasu@gmail.com\"}]}{\"name\" : \"Nobunaga\",\"email\" : \"nobunaga@gmail.com\",\"friends\" : [{\"name\" : \"Hideyoshi\",\"email\" : \"hideyoshi@gmail.com\"}, {\"name\" : \"Ieyasu\",\"email\" : \"ieyasu@gmail.com\"}]}";

        // JSON文字列 → オブジェクト
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);

        // オブジェクト → JSON文字列
        System.out.println(mapper.writeValueAsString(person));
    }

}
