package lombok.sample;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("javadoc")
@AllArgsConstructor
@Data
public class AllArgsConstructorSample {

    private int intValue;

    private String stringValue;

    public static void main(String[] args) {
        AllArgsConstructorSample instance = new AllArgsConstructorSample(1, "abc");

        // AllArgsConstructorSample(intValue=1, stringValue=abc)
        System.out.println(instance);
    }

}
