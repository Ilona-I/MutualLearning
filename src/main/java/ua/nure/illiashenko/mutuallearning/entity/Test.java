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
public class Test {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @Column(nullable = false)
    private String title;

    private Integer numberOfAttempts;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy="test")
    private Set<UserTest> userTests;

    @OneToMany(mappedBy="test")
    private Set<Question> questions;
}
