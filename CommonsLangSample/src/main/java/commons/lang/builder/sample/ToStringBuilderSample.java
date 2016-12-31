package commons.lang.builder.sample;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

@SuppressWarnings("javadoc")
@Data
@AllArgsConstructor
public class ToStringBuilderSample {

    private String string;

    private int intValue;

    private long lognValue;

    private List<NestedClass> nestedClasses;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Data
    @AllArgsConstructor
    private static class NestedClass {

        private String string;

        private int intValue;

        private long lognValue;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static void main(String[] args) {
        List<NestedClass> nestedClasses = Lists.newArrayList(
                new NestedClass("string 1", 1, 1000L),
                new NestedClass("string 2", 2, 1000L),
                new NestedClass("string 3", 3, 1000L));
        ToStringBuilderSample commonsLangSample = new ToStringBuilderSample("string 1", 1, 1000L, nestedClasses);
        System.out.println(commonsLangSample);
    }

}
