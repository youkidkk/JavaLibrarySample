package mockito.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

/**
 * mock の defaultAnswer のサンプル
 */
public class MockDefaultAnswerSample {

    @SuppressWarnings("javadoc")
    public static class Hoge {
        private String field = "hoge hoge";

        public Fuga getFuga() {
            return new Fuga();
        }

        @Override
        public boolean equals(Object obj) {
            return ((Hoge) obj).field.equals(this.field);
        }
    }

    @SuppressWarnings("javadoc")
    public static class Fuga {
        private String field = "fuga fuga";

        public Piyo getPiyo() {
            return new Piyo();
        }

        @Override
        public boolean equals(Object obj) {
            return ((Fuga) obj).field.equals(this.field);
        }
    }

    @SuppressWarnings("javadoc")
    public static class Piyo {
        private String field = "piyo piyo";

        public int getValue() {
            return 123;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Piyo) obj).field.equals(this.field);
        }
    }

    /**
     * defaultAnswer を指定しない場合
     */
    @Test
    public void whenDefault() {
        // defaultAnswer を指定せずにモック生成
        Hoge hoge = mock(Hoge.class);

        // モックのメソッドの戻り値は null となる
        Fuga fuga = hoge.getFuga();
        assertThat(fuga, is(nullValue()));

        // 以下は getPiyo() にて NullPointerException が発生する
        // hoge.getFuga().getPiyo().getValue();
    }

    /**
     * RETURNS_DEEP_STUBS を指定した場合
     */
    @Test
    public void whenReturnsDeepStub() {
        // defaultAnswer に RETURNS_DEEP_STUBS を指定してモック生成
        Hoge hoge = mock(Hoge.class, RETURNS_DEEP_STUBS);

        // モックのメソッドの戻り値はモックとなる
        Fuga fuga = hoge.getFuga();
        assertThat(fuga, notNullValue());
        assertThat(fuga, not(new Fuga()));

        Piyo piyo = fuga.getPiyo();
        assertThat(piyo, notNullValue());
        assertThat(piyo, not(new Piyo()));
        assertThat(piyo.getValue(), is(0));

        // when～ による設定も可能
        when(hoge.getFuga().getPiyo().getValue()).thenReturn(321);
        assertThat(hoge.getFuga().getPiyo().getValue(), is(321));
    }

    /**
     * CALLS_REAL_METHODS を指定した場合
     */
    @Test
    public void whenCallRealMethods() {
        // defaultAnswer に CALLS_REAL_METHODS を指定してモック生成
        Hoge hoge = mock(Hoge.class, CALLS_REAL_METHODS);

        // モックのメソッドは本来のメソッドが呼ばれている
        Fuga fuga = hoge.getFuga();
        assertThat(fuga, is(new Fuga()));
        Piyo piyo = fuga.getPiyo();
        assertThat(piyo, is(new Piyo()));
    }

}
