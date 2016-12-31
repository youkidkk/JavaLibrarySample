package powermockito.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PowerMockito テスト対象クラス
 */
public class TestTarget {

    /** ロガー */
    private static Logger logger = LoggerFactory.getLogger(TestTarget.class);

    /**
     * 引数なしコンストラクタ
     */
    public TestTarget() {
        super();
        logger.info("引数なしのコンストラクタが呼ばれました。");
    }

    /**
     * 引数ありしコンストラクタ
     *
     * @param arg 引数
     */
    public TestTarget(int arg) {
        super();
        logger.info("引数ありのコンストラクタが呼ばれました。");
    }

    /**
     * パブリック静的メソッド
     *
     * @param value 値
     * @return 結果
     */
    public static String publicStaticMethod(int value) {
        return privateStaticMethod(value);
    }

    /**
     * パブリックメソッド
     *
     * @param value 値
     * @return 結果
     */
    public String publicMethod(int value) {
        return this.privateMethod(value);
    }

    /** privateStaticMethod の戻り値 */
    public static final String PRIVATE_STATIC_METHOD_RESULT = "PrivateStaticMethod is called.";

    /**
     * プライベート静的メソッド
     *
     * @param value 値
     * @return 結果
     */
    private static String privateStaticMethod(int value) {
        return PRIVATE_STATIC_METHOD_RESULT;
    }

    /** privateMethod の戻り値 */
    public static final String PRIVATE_METHOD_RESULT = "PrivateMethod is called.";

    /**
     * プライベートメソッド
     *
     * @param value 値
     * @return 結果
     */
    private String privateMethod(int value) {
        return PRIVATE_METHOD_RESULT;
    }

}
