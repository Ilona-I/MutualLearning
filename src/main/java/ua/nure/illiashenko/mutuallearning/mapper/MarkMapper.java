package ua.nure.illiashenko.mutuallearning.mapper;

import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.entity.Mark;

@Component
public class MarkMapper {

    public MarkResponse mapToMarkResponse(Mark mark) {
        return MarkResponse.builder()
            .id(mark.getId())
            .title(mark.getTitle())
            .type(mark.getType())
            .creator(mark.getCreator())
            .description(mark.getDescription())
            .build();
    }
}
