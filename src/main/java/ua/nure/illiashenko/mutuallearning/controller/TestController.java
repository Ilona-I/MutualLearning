package ua.nure.illiashenko.mutuallearning.controller;

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
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.SaveTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.TestInfoResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestToUpdateResponse;
import ua.nure.illiashenko.mutuallearning.service.TestService;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    public TestService testService;

    @PostMapping
    public TestInfoResponse createTest(@RequestBody SaveTestRequest saveTestRequest) {
        return testService.createTest(saveTestRequest);
    }

    @PutMapping("/{id}")
    public TestInfoResponse editTest(@PathVariable int id, @RequestBody SaveTestRequest saveTestRequest) {
        return testService.editTest(id, saveTestRequest);
    }

    @GetMapping("/update/{id}")
    public TestToUpdateResponse getTestToUpdate(@PathVariable int id) {
        return testService.getTestToUpdate(id);
    }

    @GetMapping("/info/{id}")
    public TestInfoResponse getTestInfo(@PathVariable int id) {
        return testService.getTestInfo(id);
    }

    @GetMapping("/{id}")
    public TestResponse getTest(@PathVariable int id) {
        return testService.getTest(id);
    }

    @PostMapping("/{id}")
    public CheckTestResponse checkTest(@PathVariable int id, @RequestBody CheckTestRequest checkTestRequest) {
        return testService.checkTest(id, checkTestRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable int id) {
        testService.deleteTest(id);
    }
}
