package uz.elmurodov.repository;

import org.springframework.stereotype.Component;
import uz.elmurodov.dto.UserCreateDto;
import uz.elmurodov.dto.UserDto;
import uz.elmurodov.entity.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class AuthUserRepository extends AbstractRepository {
    public Long create(UserCreateDto userCreateDto) {
        String json = gson.toJson(userCreateDto);
        Long result = -1l;
        try {
            CallableStatement callableStatement = connection.prepareCall("select create_user(?)");
            callableStatement.setString(1, json);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                result =  resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
}
