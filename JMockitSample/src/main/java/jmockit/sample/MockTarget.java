package jmockit.sample;

@SuppressWarnings("javadoc")
public class MockTarget {

    public String publicMethod(String string) {
        return "publicMethod : " + string;
    }

    public static String staticPublicMethod(String string) {
        return "staticPublicMethod : " + string;
    }

}
