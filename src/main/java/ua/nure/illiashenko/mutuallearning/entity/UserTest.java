package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTest {

    private int id;
    private String userLogin;
    private int testId;
    private int mark;
    private Timestamp dateTime;
}
