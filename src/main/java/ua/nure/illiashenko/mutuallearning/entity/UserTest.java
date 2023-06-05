package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UserTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="user_login", nullable=false)
    private String userLogin;
    @Column(name="test_id")
    private Integer testId;
    private int mark;
    @Column(name = "date_time")
    private Timestamp dateTime;
}
