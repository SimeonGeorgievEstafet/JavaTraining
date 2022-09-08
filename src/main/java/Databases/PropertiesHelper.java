package Databases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class will produce ready to use date from properties file.
 */
public class PropertiesHelper {

    private static final String FILE_PATH = "src/main/resources/config.properties";
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    private static final PropertiesHelper propertiesHelper;

    static {
        try {
            propertiesHelper = new PropertiesHelper();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesHelper() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(FILE_PATH);
        properties.load(fileReader);
        this.dbUrl = properties.getProperty("db.conn.url");
        this.dbUser = properties.getProperty("db.username");
        this.dbPassword = properties.getProperty("db.password");
    }

    public static PropertiesHelper getInstance() {
        return propertiesHelper;
    }

    public String getDbUrl() {
        return this.dbUrl;
    }

    public String getDbUser() {
        return this.dbUser;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }


}
