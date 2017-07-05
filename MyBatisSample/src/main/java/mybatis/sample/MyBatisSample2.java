package mybatis.sample;

import mybatis.sample.mapper.Test2Mapper;
import mybatis.sample.model.Test2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("javadoc")
public class MyBatisSample2 {

    public static void main(String[] args) throws IOException {
        // ★ルートとなる設定ファイルを読み込む
        try (InputStream in = MyBatisSample2.class.getResourceAsStream("/mybatis-config.xml")) {
            // ★設定ファイルを元に、 SqlSessionFactory を作成する
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

            // ★SqlSessionFactory から SqlSession を生成する
            try (SqlSession session = factory.openSession()) {
                Test2 test2 = new Test2();
                test2.setText("hoge hoge");
                test2.setObj("hoge fuga piyo".getBytes());

                // ★Mapper を使って SQL を実行する
                Test2Mapper mapper = session.getMapper(Test2Mapper.class);
                mapper.insert(test2);

                session.commit();
            }

            try (SqlSession session = factory.openSession()) {
                // ★Mapper を使って SQL を実行する
                Test2Mapper mapper = session.getMapper(Test2Mapper.class);
                Test2 test2 = mapper.selectByPrimaryKey(2L);
                System.out.println(test2);

                session.commit();
            }
        }
    }

}
