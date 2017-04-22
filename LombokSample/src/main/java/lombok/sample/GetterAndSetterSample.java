package lombok.sample;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("javadoc")
public class GetterAndSetterSample {

    /**
     * staticフィールドでも設定可能。
     * Getter、Setterともstaticメソッドとなる。
     */
    @Getter
    @Setter
    private static String staticField;

    /** フィールド */
    @Getter
    @Setter
    private String field;

    public static void main(String[] args) {
        GetterAndSetterSample.setStaticField("hoge");
        System.out.println("staticField : " + GetterAndSetterSample.getStaticField());

        GetterAndSetterSample sample = new GetterAndSetterSample();
        sample.setField("fuga");
        System.out.println("field : " + sample.getField());
    }

}
