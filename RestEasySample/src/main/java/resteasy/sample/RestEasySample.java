package resteasy.sample;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;

import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * RestEasyサンプル
 */
public class RestEasySample {

    @SuppressWarnings("javadoc")
    public static void main(String[] args) throws Exception {

        request(TestResponseType.class);
    }

    @SuppressWarnings("javadoc")
    public static <T> void request(Class<T> clazz) throws Exception {
        ClientRequest req = new ClientRequest("http://localhost/hello");
        req.accept(MediaType.APPLICATION_JSON);
        req.header("Content-Type", "application/json");

        ClientResponse<List<TestResponseType>> res = req.get(new GenericType<List<T>>() {
        });

        Object obj = res.getEntity();
        System.out.println(obj);
    }

}
