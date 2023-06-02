package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UserTest {

    @Id
    private int id;
    @Column(name="user_login", nullable=false)
    private String user_login;
    @Column(name="test_id")
    private Integer testId;
    private int mark;
    @Column(name = "start_date_time")
    private Timestamp startDateTime;
    @Column(name = "finish_date_time")
    private Timestamp finishDateTime;
}
