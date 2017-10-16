package jmockit.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Test;

/**
 * モック化サンプル。
 */
public class MockingSample {

    /** モック */
    @Mocked
    MockTarget target;

    /**
     * コンストラクタのモック化。
     */
    @Test
    public void mockConstructor() {
        new Expectations() {
            {
                // コンストラクタ呼び出し時に特定のモックオブジェクトを返却するように設定
                new MockTarget("hoge");
                result = target;
                times = 1;

                // モックオブジェクトの振る舞いを設定
                target.publicMethod("fuga");
                result = "Fake publicMethod";
                times = 1;
            }
        };
        MockTarget result = new MockTarget("hoge");
        assertThat(result.publicMethod("fuga"), is("Fake publicMethod"));
    }

    /**
     * public メソッドのモック化。
     */
    @Test
    public void mockPublicMethod() {
        new Expectations() {
            {
                target.publicMethod("hoge");
                result = "Fake publicMethod";
                times = 1;
            }
        };
        assertThat(target.publicMethod("hoge"),
                is("Fake publicMethod"));
    }

    /**
     * public static メソッドのモック化。
     */
    @Test
    public void mockPublicStaticMethod() {
        new Expectations() {
            {
                MockTarget.publicStaticMethod("hoge");
                result = "Fake publicStaticMethod";
                times = 1;
            }
        };
        assertThat(MockTarget.publicStaticMethod("hoge"),
                is("Fake publicStaticMethod"));
    }

    /**
     * protected メソッドのモック化。
     *
     * @throws Exception 例外
     */
    @Test
    public void mockProtectedMethod() throws Exception {
        new Expectations() {
            {
                Deencapsulation.invoke(target, "protectedMethod", "hoge");
                result = "Fake protectedMethod";
                times = 1;
            }
        };
        assertThat(Deencapsulation.invoke(target, "protectedMethod", "hoge"),
                is("Fake protectedMethod"));
    }

    /**
     * protected static メソッドのモック化。
     *
     * @throws Exception 例外
     */
    @Test
    public void mockProtectedStaticMethod() throws Exception {
        new Expectations() {
            {
                Deencapsulation.invoke(target, "protectedStaticMethod", "hoge");
                result = "Fake protectedStaticMethod";
                times = 1;
            }
        };
        assertThat(Deencapsulation.invoke(target, "protectedStaticMethod", "hoge"),
                is("Fake protectedStaticMethod"));
    }

    /**
     * private メソッドのモック化。
     *
     * @throws Exception 例外
     */
    @Test
    public void mockPrivateMethod() throws Exception {
        new MockUp<MockTarget>() {
            @Mock
            private String privateMethod(String arg) {
                assertThat(arg, is("hoge"));
                return "Fake privateMethod";
            }
        };
        assertThat(Deencapsulation.invoke(target, "privateMethod", "hoge"),
                is("Fake privateMethod"));
    }

    /**
     * private static メソッドのモック化。
     *
     * @throws Exception 例外
     */
    @Test
    public void mockPrivateStaticMethod() throws Exception {
        new MockUp<MockTarget>() {
            @Mock
            // ※ static修飾子を付与しないこと
            private String privateStaticMethod(String arg) {
                assertThat(arg, is("hoge"));
                return "Fake privateStaticMethod";
            }
        };
        assertThat(Deencapsulation.invoke(target.getClass(), "privateStaticMethod", "hoge"),
                is("Fake privateStaticMethod"));
    }

}
