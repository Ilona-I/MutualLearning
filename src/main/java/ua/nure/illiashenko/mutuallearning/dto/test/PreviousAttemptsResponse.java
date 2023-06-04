package ua.nure.illiashenko.mutuallearning.dto.test;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreviousAttemptsResponse {

    private Timestamp dateTime;
    private Integer mark;
}
