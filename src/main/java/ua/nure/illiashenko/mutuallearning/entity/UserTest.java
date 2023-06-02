package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UserTest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_login", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    private Test test;

    @Column(nullable = false)
    private int mark;

    @Column(nullable = false)
    private Timestamp startDateTime;

    @Column(nullable = false)
    private Timestamp finishDateTime;
}
