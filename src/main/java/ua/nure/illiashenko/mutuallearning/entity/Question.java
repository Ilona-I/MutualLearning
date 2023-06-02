package ua.nure.illiashenko.mutuallearning.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    private Test test;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy="question")
    private Set<Answer> answers;
}
