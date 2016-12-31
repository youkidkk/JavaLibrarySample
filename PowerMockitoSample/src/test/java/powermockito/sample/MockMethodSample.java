package powermockito.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
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
import youkidkk.util.test.TestUtil;
import youkidkk.util.test.rule.LoggingRule;

import java.util.Arrays;

/**
 * メソッドのモック化サンプル
 */
@RunWith(PowerMockRunner.class)
public class MockMethodSample {

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
     * プライベートメソッドのモック化と実行
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mockPrivateMethod() throws Exception {
        int arg = 1;

        // モックの作成
        TestTarget mock = PowerMockito.spy(new TestTarget());

        // メソッド : privateMethod が呼ばれた際に文字列を返却する
        String privateMethodResult = "PrivateMethod is mocked.";
        PowerMockito.when(mock, "privateMethod", arg).thenReturn(privateMethodResult);

        // プライベートメソッドの呼び出し
        String result = TestUtil.invokePrivateMethod(TestTarget.class, mock, "privateMethod",
                Arrays.asList(arg), Arrays.asList(int.class));

        // 結果の確認
        assertThat(result, is(privateMethodResult));
        PowerMockito.verifyPrivate(mock, times(1)).invoke("privateMethod", 1);
    }

    /**
     * プライベート静的メソッドのモック化と実行
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mockPrivateStaticMethod() throws Exception {
        int arg = 1;

        // モックの作成
        PowerMockito.spy(TestTarget.class);

        // メソッド : privateStaticMethod が呼ばれた際に文字列を返却する
        String privateStaticMethodResult = "PrivateStaticMethod is mocked.";
        PowerMockito.when(TestTarget.class, "privateStaticMethod", arg)
                .thenReturn(privateStaticMethodResult);

        // プライベート静的メソッドの呼び出し
        String result = TestUtil.invokePrivateStaticMethod(TestTarget.class,
                "privateStaticMethod",
                Arrays.asList(arg), Arrays.asList(int.class));

        // 結果の確認
        assertThat(result, is(privateStaticMethodResult));
    }

}
