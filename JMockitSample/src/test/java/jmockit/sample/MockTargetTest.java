package jmockit.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class MockTargetTest {

    @Test
    public void testPublicMethod() {
        String ret = new MockTarget().publicMethod("test");
        assertThat(ret, is("publicMethod : test"));
    }

    @Test
    public void testStaticPublicMethod() {
        String ret = MockTarget.staticPublicMethod("test");
        assertThat(ret, is("staticPublicMethod : test"));
    }

}
