package snakeyaml.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@SuppressWarnings("javadoc")
@Data
public class SampleBean {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Nest {
        private String name;
        private String value;
    }

    private Integer number;
    private String text;
    private List<String> list;
    private List<Nest> nestList;

}
