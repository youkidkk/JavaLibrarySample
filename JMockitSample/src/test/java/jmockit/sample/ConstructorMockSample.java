package jmockit.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * コンストラクタモック化サンプル。
 */
public class ConstructorMockSample {

    @Data
    @AllArgsConstructor
    @SuppressWarnings("javadoc")
    public static class Hoge {

        public Hoge() {
        }

        private int fuga;

    }

    /**
     * コンストラクタのモック化（バージョン1.28の場合）。
     * 例外をスロー出来るだけ。
     */
    @Test(expected = UnsupportedEncodingException.class)
    public void test1() {
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

    /**
     * コンストラクタのモック化（バージョン1.30の場合）。
     */
    @Test
    public void test2(@Mocked Hoge hogeMock) {
        new Expectations() {

            {
                new Hoge(123);
                this.result = hogeMock;
                this.times = 1;
                hogeMock.getFuga();
                this.result = 321;
                this.times = 1;
            }
        };
        Hoge hogeInstance = new Hoge(123);
        assertThat(hogeInstance, sameInstance(hogeInstance));
        assertThat(hogeInstance.getFuga(), is(321));
    }

}
