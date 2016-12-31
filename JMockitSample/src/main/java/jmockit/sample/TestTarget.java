package jmockit.sample;

@SuppressWarnings("javadoc")
public class TestTarget {

    public String callMockTargetPublicMethod(String string) {
        MockTarget mockTarget = new MockTarget();
        return mockTarget.publicMethod(string);
    }

    public String callMockTargetStaticPublicMethod(String string) {
        return MockTarget.staticPublicMethod(string);
    }

    public String callThisPrivateMethod(String string) {
        return this.privateMethod(string);
    }

    private String privateMethod(String string) {
        return string;
    }

}
