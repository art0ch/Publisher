import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Generator {
    private static final Logger logger = LoggerFactory.getLogger(Generator.class);
    static String json;

    public Generator() {
        generateJSON();
    }

    void generateJSON() {
        logger.info("Class Generator has been created");

        Random random = new Random();
        long msisdn = random.nextInt(Integer.MAX_VALUE - 1 + 1) + 1;

        boolean actionRes = random.nextBoolean();
        String action;
        if (actionRes) {
            action = "PURCHASE";
        } else {
            action = "SUBSCRIPTION";
        }

        long timestamp = Instant.now().getEpochSecond();

        Map<String, Object> map = new HashMap<>();
        map.put("msisdn", msisdn);
        map.put("action", action);
        map.put("timestamp", timestamp);
        JSONObject jo = new JSONObject(map);

        json = jo.toString();
        logger.info("JSON string is {}", json);
    }

    public String getJson() {
        return json;
    }
}


