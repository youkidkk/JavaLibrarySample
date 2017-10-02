package jmockit.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("javadoc")
@RunWith(JMockit.class)
public class TestTargetTest {

    private String testParamString = "Test String";

    private String testRetString = "Return String";

    @Test
    public void testCallMockTargetPublicMethod(@Mocked MockTarget mockTarget) {
        new Expectations() {

            {
                mockTarget.publicMethod(TestTargetTest.this.testParamString);
                this.result = TestTargetTest.this.testRetString;
            }
        };
        TestTarget testTarget = new TestTarget();
        assertThat(testTarget.callMockTargetPublicMethod(this.testParamString),
                is(this.testRetString));
    }

    @Test
    public void testCallMockTargetStaticPublicMethod(@Mocked MockTarget mockTarget) {
        new Expectations() {

            {
                MockTarget.staticPublicMethod(TestTargetTest.this.testParamString);
                this.result = TestTargetTest.this.testRetString;
            }
        };
        assertThat(new TestTarget().callMockTargetStaticPublicMethod(this.testParamString),
                is(this.testRetString));
    }

    @Test
    @Ignore
    public void testCallPrivateMethod() {
        // JMockit 1.26 まで
        new MockUp<TestTarget>() {

            @Mock
            private String privateMethod(String string) {
                return TestTargetTest.this.testRetString;
            }
        };
        TestTarget testTarget = new TestTarget();
        String ret = testTarget.callThisPrivateMethod(this.testParamString);
        assertThat(ret, is(this.testRetString));
    }

    @Test
    public void testPrivateMethod() {
        TestTarget testTarget = new TestTarget();
        String ret = Deencapsulation.invoke(testTarget, "privateMethod", this.testParamString);
        assertThat(ret, is(this.testParamString));
    }

}
