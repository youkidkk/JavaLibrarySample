package mockito.sample;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @InjectMocks のサンプル
 */
public class InjectMocksSample {

    @SuppressWarnings("javadoc")
    private static class Hoge {
        private Fuga fuga = new Fuga();

        public Fuga getFuga() {
            return this.fuga;
        }
    }

    @SuppressWarnings("javadoc")
    private static class Fuga {
        private String value = "Fuga Value";

        public String getValue() {
            return this.value;
        }
    }

    /**
     * @InjectMocks を使用するためのルール設定
     * ルールを使用せずに以下でもOK
     * @Before
     * public void setup() {
     *     MockitoAnnotations.initMocks(this);
     * }
     */
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    /**
     * Hogeクラスのモック
     * @InjectMocks により、フィールド：fuga がモックになる
     */
    @InjectMocks
    Hoge hogeMock;

    /**
     * Fugaクラスのモック
     */
    @Mock
    Fuga fugaMock;

    /**
     * InjectMocks のテスト
     */
    @Test
    public void test() {
        Fuga fuga = this.hogeMock.getFuga();
        // Hoge のフィールド：fuga がモック化されているため、
        // メソッドの戻り値が null になる
        assertThat(fuga.getValue(), nullValue());

        // fugaMock のメソッド戻り値を設定する
        when(this.fugaMock.getValue()).thenReturn("Mocked Fuga!!!");
        // hogeMock#getFuga が上記の通りの動作になる
        assertThat(this.hogeMock.getFuga().getValue(), is("Mocked Fuga!!!"));
    }

}
