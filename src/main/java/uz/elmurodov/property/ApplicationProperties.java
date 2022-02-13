package uz.elmurodov.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
public class ApplicationProperties {
    private static Properties properties;
    static {
        try {
            if (Objects.isNull(properties)) {
                properties = new Properties();
                properties.load(new FileReader("src/main/resources/application.properties"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
