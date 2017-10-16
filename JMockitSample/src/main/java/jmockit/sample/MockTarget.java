package jmockit.sample;

/**
 * モック化対象クラス。
 */
public class MockTarget {

    /** フィールド */
    String field;

    /**
     * コンストラクタ。
     *
     * @param arg 引数
     */
    public MockTarget(String arg) {
        field = arg;
    }

    /**
     * public メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    public String publicMethod(String arg) {
        return "publicMethod : " + arg;
    }

    /**
     * public static メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    public static String publicStaticMethod(String arg) {
        return "publicStaticMethod : " + arg;
    }

    /**
     * protected メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    @SuppressWarnings("unused")
    protected String protectedMethod(String arg) {
        return "protectedMethod : " + arg;
    }

    /**
     * protected static メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    @SuppressWarnings("unused")
    protected static String protectedStaticMethod(String arg) {
        return "protectedStaticMethod : " + arg;
    }

    /**
     * private メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    @SuppressWarnings("unused")
    private String privateMethod(String arg) {
        return "privateMethod : " + arg;
    }

    /**
     * private static メソッド。
     *
     * @param arg 引数
     * @return 戻り値
     */
    @SuppressWarnings("unused")
    private static String privateStaticMethod(String arg) {
        return "privateStaticMethod : " + arg;
    }

}
