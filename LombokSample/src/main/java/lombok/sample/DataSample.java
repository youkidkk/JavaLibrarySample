package lombok.sample;

import lombok.Data;

import java.text.MessageFormat;

/**
 * 「@Data」 サンプル
 * 以下を全て付与するのと同義
 *   ・@Getter
 *   ・@Setter
 *   ・@RequiredArgsConstructor
 *   ・@ToString
 *   ・@EqualsAndHashCode
 */
@SuppressWarnings("javadoc")
@Data // クラスにアノテーションを付与
public class DataSample {

    private int intField;

    private String stringField;

    public static void main(String[] args) {
        DataSample dataSample = new DataSample();

        // フィールドへの Setter が追加されている
        dataSample.setIntField(123);
        dataSample.setStringField("abc");

        // フィールドへの Getter が追加されている
        int i = dataSample.getIntField();
        String s = dataSample.getStringField();
        System.out.println(
                MessageFormat.format("intField = {0}, stringField = {1}", i, s));

        // フィールドを使用した toString メソッドも追加されている
        System.out.println(dataSample);
    }

}
