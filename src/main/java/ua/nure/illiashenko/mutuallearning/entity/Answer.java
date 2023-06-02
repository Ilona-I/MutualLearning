package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Answer {

    @Id
    private Integer id;
    @Column(name = "question_id")
    private Integer questionId;
    private String text;
    private Integer point;
}
