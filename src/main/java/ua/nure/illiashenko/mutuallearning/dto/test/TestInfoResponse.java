package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestInfoResponse {

    private String title;
    private Integer maxNumberOfAttempts;
    private Integer maxMark;
    private UsersAttemptsResponse[] usersAttemptsResponse;
}
