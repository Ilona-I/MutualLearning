package ua.nure.illiashenko.mutuallearning.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ua.nure.illiashenko.mutuallearning.dto.mark.CreateMarkRequest;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.UpdateMarkRequest;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.validation.MarkValidator;

@Slf4j
@Service
public class MarkService {

    @Autowired
    private MarkValidator markValidator;
    @Autowired
    private MarkRepository markRepository;

    public MarkResponse createMark(CreateMarkRequest createMarkRequest){
        return null;
    }

    public MarkResponse getMark(int id){
        return null;
    }

    public MarkResponse updateMark(UpdateMarkRequest updateMarkRequest){
        return null;

    }

    public List<MarkResponse> getMarkList() {
        return null;
    }

    public void deleteMark(int id) {

    }
}
