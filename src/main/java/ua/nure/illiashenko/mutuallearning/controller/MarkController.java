package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.mark.CreateMarkRequest;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.UpdateMarkRequest;
import ua.nure.illiashenko.mutuallearning.service.MarkService;

@RestController
@RequestMapping("/marks")
@AllArgsConstructor
public class MarkController {

    @Autowired
    private MarkService markService;

    @PostMapping
    public MarkResponse createMark(@RequestBody CreateMarkRequest createMarkRequest) {
        return markService.createMark(createMarkRequest);
    }

    @PutMapping
    public MarkResponse updateMark(@RequestBody UpdateMarkRequest updateMarkRequest) {
        return markService.updateMark(updateMarkRequest);
    }

    @GetMapping()
    public List<MarkResponse> getMarkList() {
     return markService.getMarkList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMark(@PathVariable int id) {
        markService.deleteMark(id);
    }
}
