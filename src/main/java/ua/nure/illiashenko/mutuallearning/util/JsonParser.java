package ua.nure.illiashenko.mutuallearning.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonParser {

    @Autowired
    private Base64Util base64Util;

    public String getLoginFromJsonString(String json) {
        try {
            final String decodedJson = base64Util.decodeString(json);
            final Map<String, String> userMap = new ObjectMapper().readValue(decodedJson, Map.class);
            if (userMap.get("login") != null && !userMap.get("login").trim().isEmpty()) {
                return userMap.get("login");
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;

    }
}
