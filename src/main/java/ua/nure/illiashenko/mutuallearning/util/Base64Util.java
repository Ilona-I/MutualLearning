package ua.nure.illiashenko.mutuallearning.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class Base64Util {

    public String decodeString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes)
            .replace("%7B","{")
            .replace("%22", "\"")
            .replace("%3A", ":")
            .replace("%2C", ",")
            .replace("%20", " ")
            .replace("%7D", "}")
            .replace("%40", "@");
    }
}
