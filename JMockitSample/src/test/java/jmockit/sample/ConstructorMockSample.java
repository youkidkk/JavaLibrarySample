package jmockit.sample;

import static org.junit.Assert.*;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * コンストラクタモック化サンプル。
 */
public class ConstructorMockSample {

    @SuppressWarnings("javadoc")
    public static class Hoge {

        private int fuga;

        public Hoge() {
        }

        public Hoge(int fuga) {
            this.fuga = fuga;
        }

        public int getFuga() {
            return this.fuga;
        }
    }

    /**
     * コンストラクタのモック化。
     */
    @Test(expected = UnsupportedEncodingException.class)
    public void test() {
        new MockUp<Hoge>() { // 対象の方を指定する

            // メソッド名を $init にして、引数を対象のコンストラクタと合わせる
            @Mock
            public void $init(int fuga) throws UnsupportedEncodingException {
                throw new UnsupportedEncodingException();
            }
        };
        Hoge hoge = new Hoge(123); // UnsupportedEncodingException発生
        fail(hoge.toString());
    }

}
