package snakeyaml.sample;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("javadoc")
public class SnakeYamlSample {

    public static final String RESOURCES_PATH = "./src/main/resources/";

    public static void main(String[] args) throws IOException {
        Yaml yaml = new Yaml();

        // ファイルからの読み込み
        SampleBean bean = yaml.loadAs(
                new FileInputStream(new File(SnakeYamlSample.RESOURCES_PATH + "sample.yml")),
                SampleBean.class);
        System.out.println("--- SampleBean ---\n" + bean);

        // String に変換
        System.out.println("--- Yaml String ---\n" + yaml.dump(bean));

        // ファイルに書き込み
        yaml.dump(
                bean,
                new FileWriter(new File(SnakeYamlSample.RESOURCES_PATH + "out.yml")));
    }

}
