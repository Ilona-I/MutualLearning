package ua.nure.illiashenko.mutuallearning.controller;

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
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.SaveTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.TestInfoResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestToUpdateResponse;
import ua.nure.illiashenko.mutuallearning.service.TestService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    public TestService testService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping("/create/{id}")
    public void createTest(@PathVariable int id, @RequestBody SaveTestRequest saveTestRequest) {
        testService.createTest(id, saveTestRequest);
    }

    @PutMapping("/{id}")
    public void editTest(@PathVariable int id, @RequestBody SaveTestRequest saveTestRequest) {
        testService.editTest(id, saveTestRequest);
    }

    @GetMapping("/update/{id}")
    public TestToUpdateResponse getTestToUpdate(@PathVariable int id) {
        return testService.getTestToUpdate(id);
    }

    @GetMapping("/info/{id}")
    public TestInfoResponse getTestInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @PathVariable int id) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return testService.getTestInfo(login, id);
    }

    @GetMapping("/{id}")
    public TestResponse getTest(@PathVariable int id) {
        return testService.getTest(id);
    }

    @PostMapping("/{id}")
    public void sendTestToCheck(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @PathVariable int id, @RequestBody CheckTestRequest checkTestRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        testService.sendTestToCheck(login, id, checkTestRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable int id) {
        testService.deleteTest(id);
    }
}
