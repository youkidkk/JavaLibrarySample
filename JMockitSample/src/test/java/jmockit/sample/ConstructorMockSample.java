package jmockit.sample;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class ConstructorMockSample {

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

    @Test(expected = UnsupportedEncodingException.class)
    public void test() {
        new MockUp<Hoge>() {

            @Mock
            public void $init(int fuga) throws UnsupportedEncodingException {
                throw new UnsupportedEncodingException();
            }
        };
        Hoge hoge = new Hoge(123);
    }

}
