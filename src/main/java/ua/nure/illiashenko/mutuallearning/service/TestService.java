package ua.nure.illiashenko.mutuallearning.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.CheckTestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.SaveTestRequest;
import ua.nure.illiashenko.mutuallearning.dto.test.TestInfoResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestResponse;
import ua.nure.illiashenko.mutuallearning.dto.test.TestToUpdateResponse;

@Slf4j
@Service
public class TestService {

    public TestInfoResponse createTest(SaveTestRequest saveTestRequest) {
        return null;
    }

    public TestInfoResponse editTest(int id, SaveTestRequest saveTestRequest) {
        return null;
    }

    public TestToUpdateResponse getTestToUpdate(int id) {
        return null;
    }

    public TestInfoResponse getTestInfo(int id) {
        return null;
    }

    public TestResponse getTest(int id) {
        return null;
    }

    public CheckTestResponse checkTest(int id, CheckTestRequest checkTestRequest) {
        return null;
    }

    public void deleteTest(int id){

    }
}
