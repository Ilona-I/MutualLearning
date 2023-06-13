package ua.nure.illiashenko.mutuallearning.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.service.AdminService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/database")
@AllArgsConstructor
public class DatabaseController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping("/backup")
    private void backup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        final String login = jsonParser.getLoginFromJsonString(authorization);
        adminService.backupDB();
    }

    @PostMapping("/restore")
    private void restore(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        final String login = jsonParser.getLoginFromJsonString(authorization);
        adminService.restoreDB();
    }
}
