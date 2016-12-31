package lombok.sample;

import lombok.Data;

@SuppressWarnings("javadoc")
@Data
public class DataSample {

    private int intValue;

    private String stringValue;

    public static void main(String[] args) {
        DataSample dataSample = new DataSample();
        dataSample.setIntValue(1);
        dataSample.setStringValue("abc");

        // DataSample(intValue=1, stringValue=abc)
        System.out.println(dataSample);
    }

}
