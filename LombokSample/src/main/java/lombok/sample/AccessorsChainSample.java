package lombok.sample;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Accessors chain サンプル
 * いわゆる流れるようなインターフェースを実現する
 */
@Accessors(chain = true)
@ToString
@SuppressWarnings("javadoc")
public class AccessorsChainSample {

    @Setter
    private String fieldA;
    @Setter
    private int fieldB;
    @Setter
    private boolean fieldC;

    public static void main(String[] args) {
        AccessorsChainSample sample = new AccessorsChainSample()
                .setFieldA("abc")
                .setFieldB(123)
                .setFieldC(true);
        System.out.println(sample);
    }

}
