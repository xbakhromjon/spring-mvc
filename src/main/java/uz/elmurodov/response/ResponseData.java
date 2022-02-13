package uz.elmurodov.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData<D> {
    private D body;
    private int count;

    public ResponseData(D body) {
        this.body = body;
        this.count = 1;
    }

    public ResponseData(D body, int count) {
        this.body = body;
        this.count = count;
    }
}
