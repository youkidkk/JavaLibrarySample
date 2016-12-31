package sample;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("javadoc")
public class MustacheSample {

    static class Path {
        String name;
        List<Prop> propList;

        Path(String name, List<Prop> propList) {
            this.name = name;
            this.propList = propList;
        }
    }

    static class Prop {
        String key;
        String value;

        Prop(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Path> pathList = new ArrayList<>();

        List<Prop> propList = new ArrayList<>();
        propList.add(new Prop("key1", "value1"));
        propList.add(new Prop("key2", "value2"));
        propList.add(new Prop("key3", "value3"));

        pathList.add(new Path("test1", propList));
        pathList.add(new Path("test2", propList));

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("template/sample.template");

        Map<String, Object> map = new HashMap<>();
        map.put("pathList", pathList);
        mustache.execute(new PrintWriter(System.out), map).flush();
    }

}
