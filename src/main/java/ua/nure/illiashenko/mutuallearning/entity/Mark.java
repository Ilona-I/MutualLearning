package ua.nure.illiashenko.mutuallearning.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Mark {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy="mark")
    private Set<ArticleMark> articleMarks;
}
