package resteasy.sample;

@SuppressWarnings("javadoc")
public class TestResponseType {

    private int number;

    private String text;

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TestResponseType() {
    }

    public TestResponseType(int number, String text) {
        super();
        this.number = number;
        this.text = text;
    }

}
