package powermockito.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youkidkk.util.test.rule.LoggingRule;

/**
 * クラスのモック化サンプル
 */
@RunWith(PowerMockRunner.class)
public class MockClassSample {

    /** ロガー */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** ルール : 予期された例外 */
    public ExpectedException thrown = ExpectedException.none();

    /** ルールチェーン */
    @Rule
    public RuleChain ruleChain = RuleChain
            .outerRule(new LoggingRule(this.logger))
            .around(this.thrown);

    /**
     * PowerMockito.mockStatic(Class) のサンプル
     *
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mockStatic() throws Exception {
        PowerMockito.mockStatic(TestTarget.class);

        String result = TestTarget.publicStaticMethod(1);

        // publicStaticMethod がモック化されているため null が返却されている
        assertThat(result, nullValue());

        // publicStaticMethod が呼ばれている
        PowerMockito.verifyStatic(times(1));
        TestTarget.publicStaticMethod(1);

        // モック化されているため、publicStaticMethod から呼ばれる privateStaticMethod は呼ばれない
        PowerMockito.verifyPrivate(TestTarget.class, times(0)).invoke("privateStaticMethod", 1);
    }

    /**
     * PowerMockito.spy(Class) のサンプル
     *
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void spyClass() throws Exception {
        PowerMockito.spy(TestTarget.class);

        // privateStaticMethod をモック化
        String privateStaticMethodResult = "PrivateStaticMethod is mocked.";
        PowerMockito.when(TestTarget.class, "privateStaticMethod", anyInt())
                .thenReturn(privateStaticMethodResult);

        String result = TestTarget.publicStaticMethod(1);

        // publicStaticMethod から呼ばれる privateStaticMethod が
        // モック化されており、その戻り値はfalseになっている
        assertThat(result, is(privateStaticMethodResult));

        // publicStaticMethod が呼ばれている
        PowerMockito.verifyStatic(times(1));
        TestTarget.publicStaticMethod(1);

        // モック化されていないため、publicStaticMethod から呼ばれる privateStaticMethod が呼ばれている
        // ※ ただし、privateStaticMethod はモック化されている
        PowerMockito.verifyPrivate(TestTarget.class, times(1)).invoke("privateStaticMethod", 1);
    }

    /**
     * PowerMockito.mock(Instance) のサンプル
     *
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mock() throws Exception {
        TestTarget mockInstance = PowerMockito.mock(TestTarget.class);

        String result = mockInstance.publicMethod(1);

        // publicMethod がモック化されているため null が返却されている
        assertThat(result, nullValue());

        // publicMethod が呼ばれている
        verify(mockInstance, times(1)).publicMethod(1);

        // モック化されているため、publicStaticMethod から呼ばれる privateMethod は呼ばれない
        PowerMockito.verifyPrivate(mockInstance, times(0)).invoke("privateMethod", 1);
    }

    /**
     * PowerMockito.spy(Instance) のサンプル
     *
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void spyInstance() throws Exception {
        TestTarget mockInstance = PowerMockito.spy(new TestTarget());

        // privateMethod をモック化
        String privateMethodResult = "PrivateMethod is mocked.";
        PowerMockito.when(mockInstance, "privateMethod", anyInt()).thenReturn(privateMethodResult);

        String result = mockInstance.publicMethod(1);

        // publicMethod から呼ばれる privateMethod が
        // モック化されており、その戻り値は privateMethodResult になっている
        assertThat(result, is(privateMethodResult));

        // publicMethod が呼ばれている
        verify(mockInstance, times(1)).publicMethod(1);

        // モック化されていないため、publicStaticMethod から呼ばれる privateMethod が呼ばれている
        // ※ ただし、privateMethod はモック化されている
        PowerMockito.verifyPrivate(mockInstance, times(1)).invoke("privateMethod", 1);
    }

}
