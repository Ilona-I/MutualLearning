package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Mark {

    @Id
    private Integer id;
    private String title;
    private String creator;
    private String type;
    private String description;
}
