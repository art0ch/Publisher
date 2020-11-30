import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;

public class Sender {
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    public Sender() {
        send();
    }

    void send() {
        logger.info("Class Sender has been created");
        Generator generator = new Generator();
        String requestBody = generator.getJson();

        try {
            URL url = new URL("http://localhost:80");
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(String.valueOf(url));
            StringEntity entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);
            logger.info("JSON has been sent");
            String responseString = new BasicResponseHandler().handleResponse(response);
            logger.info(String.valueOf(response));
            logger.info(responseString);
            client.close();
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }
}

