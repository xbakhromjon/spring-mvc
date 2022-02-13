package uz.elmurodov.mappers;

import org.springframework.stereotype.Component;
import uz.elmurodov.dto.UserCreateDto;
import uz.elmurodov.entity.User;
@Component
public class UserMapper {
    public User fromCreateDto(UserCreateDto userCreateDto) {
        return User.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .email(userCreateDto.getEmail())
                .phone_number(userCreateDto.getPhoneNumber())
                .build();
    }
}
