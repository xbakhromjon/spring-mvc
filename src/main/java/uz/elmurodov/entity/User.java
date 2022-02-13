package uz.elmurodov.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
public class User extends Auditable{
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    @Builder
    public User(Long id, Date created_at, Long created_by, Date updated_at, Long updated_by, String username, String password, String email, String phone_number) {
        super(id, created_at, created_by, updated_at, updated_by);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phone_number;
    }
}
