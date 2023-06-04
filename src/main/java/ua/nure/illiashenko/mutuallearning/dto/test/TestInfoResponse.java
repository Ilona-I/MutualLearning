package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestInfoResponse {

    private String title;
    private Integer maxMark;
    private PreviousAttemptsResponse[] ownPreviousAttempts;
    private String role;
    private Integer userCount;
    private Double userAverageMark;
    private UsersAttemptsResponse[] usersAttemptsResponse;
}
