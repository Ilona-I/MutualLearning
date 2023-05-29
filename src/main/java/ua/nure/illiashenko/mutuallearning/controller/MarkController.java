package ua.nure.illiashenko.mutuallearning.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;

@RestController
@RequestMapping("/marks")
@AllArgsConstructor
public class MarkController {

    @GetMapping()
    public List<MarkResponse> getMarkList() {
        List<MarkResponse> result = new ArrayList<>();
        result.add(MarkResponse.builder()
            .id(4)
            .title("Mark4")
            .creator("user4")
            .type("custom")
            .description("Description4")
            .build());
        result.add(
            MarkResponse.builder()
                .id(5)
                .title("Mark5")
                .type("system")
                .description("Description5")
                .build());
        result.add(
            MarkResponse.builder()
                .id(6)
                .title("Mark6")
                .type("system")
                .description("Description6")
                .build());
        result.add(MarkResponse.builder()
            .id(1)
            .title("Інженерія програмного забезпечення")
            .creator("user1")
            .type("custom")
            .description("Description1")
            .build());
        result.add(
            MarkResponse.builder()
                .id(2)
                .title("Mark2")
                .creator("user2")
                .type("custom")
                .description("Description2")
                .build()
        );
        result.add(
            MarkResponse.builder()
                .id(3)
                .title("Mark3")
                .type("system")
                .description("Description3")
                .build());
        result.add(MarkResponse.builder()
            .id(7)
            .title("Mark7")
            .creator("user1")
            .type("custom")
            .description("Description7")
            .build());
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMark(@PathVariable int id) {
        System.out.println("delete mark: " + id);
    }

}
