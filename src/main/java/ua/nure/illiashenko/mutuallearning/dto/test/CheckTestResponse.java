package ua.nure.illiashenko.mutuallearning.dto.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckTestResponse {

    private Integer mark;
    private Integer maxMark;
}
