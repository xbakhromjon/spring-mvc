package uz.elmurodov.repository;

import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.ApplicationContext;
import uz.elmurodov.config.Context;
import uz.elmurodov.property.ApplicationProperties;

import java.lang.reflect.GenericSignatureFormatError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public abstract class AbstractRepository {
    // TODO: 2/13/2022 Database connection
    ApplicationProperties applicationProperties = Context.applicationContext.getBean(ApplicationProperties.class);
    public Connection connection;
    public Gson gson = new Gson();
    public AbstractRepository() {
        try {
            connection = DriverManager.getConnection(applicationProperties.get("db.connection"),
                    applicationProperties.get("db.username"), applicationProperties.get("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (Objects.nonNull(connection) && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
