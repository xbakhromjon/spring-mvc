package uz.elmurodov.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ResponseEntity<D> {
    private HttpStatus status;
    private ResponseData<D> responseData;

    public ResponseEntity(ResponseData<D> responseData) {
        this.responseData = responseData;
        this.status = HttpStatus.OK;
    }

    public ResponseEntity(HttpStatus status, ResponseData<D> responseData) {
        this.status = status;
        this.responseData = responseData;
    }
}
