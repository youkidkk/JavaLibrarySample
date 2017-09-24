package xmlbeans.sample;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import java.io.IOException;
import java.nio.file.Paths;

@SuppressWarnings("javadoc")
public class XmlBeansSample {

    public static void main(String[] args) throws IOException, XmlException {
        XmlObject xml = XmlObject.Factory.parse(Paths.get("./xml/sample.xml").toFile());

        System.out.println(xml);
        XmlCursor cursor = xml.newCursor();
        cursor.toFirstChild();
        cursor.toChild("test");
        cursor.insertElementWithText("test", "test value4");
        System.out.println(xml);
        System.out.println(cursor.getTextValue());
    }

}
