package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Question {

    @Id
    private Integer id;
    @Column(name = "test_id")
    private Integer testId;
    private String text;
    private String type;
}
