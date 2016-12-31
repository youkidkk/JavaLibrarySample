package mybatis.sample;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@SuppressWarnings("javadoc")
public class MyBatisSample {

    public static void main(String[] args) throws IOException {
        // ★ルートとなる設定ファイルを読み込む
        try (InputStream in = MyBatisSample.class.getResourceAsStream("/mybatis-config.xml")) {
            // ★設定ファイルを元に、 SqlSessionFactory を作成する
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

            // ★SqlSessionFactory から SqlSession を生成する
            try (SqlSession session = factory.openSession()) {
                // ★SqlSession を使って SQL を実行する
                List<Map<String, Object>> result = session.selectList("sample.mybatis.selectTest");

                result.forEach(row -> {
                    System.out.println("---------------");
                    row.forEach((columnName, value) -> {
                        System.out.printf("columnName=%s, value=%s%n", columnName, value);
                    });
                });
            }
        }
    }

}
