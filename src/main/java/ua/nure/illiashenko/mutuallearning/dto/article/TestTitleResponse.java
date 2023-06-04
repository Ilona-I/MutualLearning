package ua.nure.illiashenko.mutuallearning.dto.article;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestTitleResponse {

    private int id;
    private String title;
}
