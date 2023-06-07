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
import ua.nure.illiashenko.mutuallearning.dto.comment.CommentResponse;
import ua.nure.illiashenko.mutuallearning.dto.comment.CreateCommentRequest;
import ua.nure.illiashenko.mutuallearning.dto.comment.UpdateCommentRequest;
import ua.nure.illiashenko.mutuallearning.service.CommentService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping
    public CommentResponse createComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody CreateCommentRequest createCommentRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return commentService.createComment(login, createCommentRequest);
    }

    @PutMapping()
    public CommentResponse updateComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @RequestBody UpdateCommentRequest updateCommentRequest) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return commentService.updateComment(login, updateCommentRequest);
    }

    @GetMapping("/{articleId}")
    public List<CommentResponse> getCommentsForArticle(@PathVariable int articleId) {
        return commentService.getCommentsForArticle(articleId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        @PathVariable int commentId) {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        commentService.deleteComment(login, commentId);
    }
}
