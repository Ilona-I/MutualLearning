package ua.nure.illiashenko.mutuallearning.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.article.Member;
import ua.nure.illiashenko.mutuallearning.dto.comment.CommentResponse;
import ua.nure.illiashenko.mutuallearning.dto.comment.CreateCommentRequest;
import ua.nure.illiashenko.mutuallearning.dto.comment.UpdateCommentRequest;
import ua.nure.illiashenko.mutuallearning.entity.Comment;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.repository.CommentRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public CommentResponse createComment(String login, CreateCommentRequest createCommentRequest) {
        final Comment comment = new Comment();
        comment.setLogin(login);
        comment.setArticleId(createCommentRequest.getArticleId());
        comment.setText(createCommentRequest.getText());
        comment.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        return getCommentResponse(login, comment);
    }

    public CommentResponse updateComment(String login, UpdateCommentRequest updateCommentRequest) {
        final Comment comment = commentRepository.findById(updateCommentRequest.getId())
            .orElseThrow(() -> new ServiceApiException(HttpStatus.NOT_FOUND));
        if(!comment.getLogin().equals(login)){
            throw new ServiceApiException(HttpStatus.FORBIDDEN);
        }
        comment.setLastUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        comment.setText(updateCommentRequest.getText());
        return getCommentResponse(login, comment);
    }

    private CommentResponse getCommentResponse(String login, Comment comment) {
        commentRepository.save(comment);
        final CommentResponse commentResponse = new CommentResponse();
        final Optional<User> optionalUser = userRepository.findById(login);
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            final Member member = Member.builder()
                .login(user.getLogin())
                .name(user.getName())
                .info(user.getInfo())
                .build();
            commentResponse.setMember(member);
        }
        commentResponse.setId(comment.getId());
        commentResponse.setCreationDateTime(comment.getCreationDateTime());
        commentResponse.setText(comment.getText());
        return commentResponse;
    }

    public List<CommentResponse> getCommentsForArticle(int articleId) {
        return commentRepository.findByArticleIdOrderByCreationDateTimeDesc(articleId)
            .stream().map(comment -> {
                final CommentResponse commentResponse = new CommentResponse();
                final Optional<User> optionalUser = userRepository.findById(comment.getLogin());
                if (optionalUser.isPresent()) {
                    final User user = optionalUser.get();
                    final Member member = Member.builder()
                        .login(user.getLogin())
                        .name(user.getName())
                        .info(user.getInfo())
                        .build();
                    commentResponse.setMember(member);
                }
                commentResponse.setId(comment.getId());
                commentResponse.setCreationDateTime(comment.getCreationDateTime());
                commentResponse.setLastUpdateDateTime(comment.getLastUpdateDateTime());
                commentResponse.setText(comment.getText());
                return commentResponse;
            }).collect(Collectors.toList());

    }

    public void deleteComment(String login, int commentId) {
        final Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ServiceApiException(
            HttpStatus.NOT_FOUND));
        if (!login.equals(comment.getLogin())) {
            throw new ServiceApiException(HttpStatus.FORBIDDEN);
        }
        commentRepository.deleteById(commentId);
    }
}
