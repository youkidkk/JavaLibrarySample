package lombok.sample;

import lombok.NonNull;

/**
 * 「@NonNull」サンプル
 */
@SuppressWarnings("javadoc")
public class NonNullSample {

    @SuppressWarnings("unused")
    private String fieldA;

    // fieldA に @NonNull を指定
    public NonNullSample(@NonNull String fieldA) {
        this.fieldA = fieldA;
    }

    public static void main(String[] args) {
        try {
            // @NonNull の引数に null を設定しているため、
            // NullPointerException がスローされる
            new NonNullSample(null);
        } catch (NullPointerException e) {
            System.out.println("Thrown - " + e);
        }
    }

}
