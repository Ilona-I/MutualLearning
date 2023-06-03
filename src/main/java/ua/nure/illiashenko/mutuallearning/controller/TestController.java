package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.service.TestService;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    public TestService testService;

}
