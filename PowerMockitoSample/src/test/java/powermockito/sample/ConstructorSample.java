package powermockito.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
 * PowerMockito コンストラクタ関連サンプル
 */
@RunWith(PowerMockRunner.class)
public class ConstructorSample {

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
     * 引数なしコンストラクタのモック化と実行
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mockConstructorWithNoArgs() throws Exception {
        this.logger.info("mockConstructorWithNoArgs 開始。");

        // モックの作成
        TestTarget mock = PowerMockito.mock(TestTarget.class);

        // 引数なしコンストラクタが呼ばれた際にモックを返却する
        PowerMockito.whenNew(TestTarget.class).withNoArguments().thenReturn(mock);

        // 実行と結果の確認
        TestTarget instance = new TestTarget();
        assertThat(instance, sameInstance(mock));
    }

    /**
     * 引数ありコンストラクタのモック化と実行
     * @throws Exception 例外
     */
    @PrepareForTest(TestTarget.class)
    @Test
    public void mockConstructorWithArgs() throws Exception {
        this.logger.info("mockConstructorWithArgs 開始。");

        // モックの作成
        TestTarget mock = PowerMockito.mock(TestTarget.class);

        // 引数ありコンストラクタが 引数：1 で呼ばれた際にモックを返却する
        PowerMockito.whenNew(TestTarget.class).withArguments(1).thenReturn(mock);

        // モック化されたコンストラクタの実行
        TestTarget instance = new TestTarget(1);
        assertThat(instance, sameInstance(mock)); // モックが返却されている

        // モック化されていないコンストラクタの実行
        TestTarget instance2 = new TestTarget(2);
        assertThat(instance2, not(sameInstance(mock))); // モックが返却されていない
    }

}
