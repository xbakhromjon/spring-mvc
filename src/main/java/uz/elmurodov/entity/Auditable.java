package uz.elmurodov.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class Auditable {
    private Long id;
    private Date created_at;
    private Long created_by;
    private Date updated_at;
    private Long updated_by;


    public Auditable(Long id, Date created_at, Long created_by, Date updated_at, Long updated_by) {
        this.id = id;
        this.created_at = created_at;
        this.created_by = created_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
    }
}
