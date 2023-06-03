package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Test {

    @Id
    private Integer id;
    @Column(name = "article_id")
    private Integer articleId;
    private String title;
    @Column(name = "number_of_attempts")
    private Integer numberOfAttempts;
}
