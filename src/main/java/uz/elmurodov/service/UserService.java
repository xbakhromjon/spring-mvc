package uz.elmurodov.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.elmurodov.dto.UserCreateDto;
import uz.elmurodov.repository.AuthUserRepository;
import uz.elmurodov.response.ResponseData;
import uz.elmurodov.response.ResponseEntity;

@Component
public class UserService extends AbstractService<AuthUserRepository> {
    public UserService(AuthUserRepository repository) {
        super(repository);
    }

    public ResponseEntity<Long> create(UserCreateDto userCreateDto) {
        Long id = repository.create(userCreateDto);
        ResponseEntity<Long> response = new ResponseEntity<Long>(new ResponseData<>(id));
        if (id == -1) {
            response.setStatus(HttpStatus.BAD_REQUEST);
        } else {
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }
}
