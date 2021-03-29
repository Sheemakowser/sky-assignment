package helpers;

import java.util.Properties;

public class TestProperties {
    private static Properties prop = new Properties();

    public static Properties loadProperties() {
        try {
            String propFilePath = "/properties/application.properties";
            prop.load(TestProperties.class.getResourceAsStream(propFilePath));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return prop;
    }
}