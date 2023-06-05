package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.mark.CreateMarkRequest;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.UpdateMarkRequest;
import ua.nure.illiashenko.mutuallearning.service.MarkService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/marks")
@AllArgsConstructor
public class MarkController {

    @Autowired
    private MarkService markService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping
    public MarkResponse createMark(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody CreateMarkRequest createMarkRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return markService.createMark(login, createMarkRequest);
    }

    @PutMapping
    public MarkResponse updateMark(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody UpdateMarkRequest updateMarkRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return markService.updateMark(login, updateMarkRequest);
    }

    @GetMapping("/{id}")
    public MarkResponse getMark(@PathVariable int id) {
        return markService.getMark(id);
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
