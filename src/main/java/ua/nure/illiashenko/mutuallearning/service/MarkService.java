package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.MarkType.CUSTOM;
import static ua.nure.illiashenko.mutuallearning.constants.MarkType.SYSTEM;
import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.ADMIN;
import static ua.nure.illiashenko.mutuallearning.constants.SystemUserRole.MODERATOR;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.mark.CreateMarkRequest;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.UpdateMarkRequest;
import ua.nure.illiashenko.mutuallearning.entity.Mark;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.exception.AccessDeniedException;
import ua.nure.illiashenko.mutuallearning.exception.mark.MarkNotFoundException;
import ua.nure.illiashenko.mutuallearning.exception.user.UserNotFoundException;
import ua.nure.illiashenko.mutuallearning.mapper.MarkMapper;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.MarkValidator;

@Slf4j
@Service
public class MarkService {

    @Autowired
    private MarkValidator markValidator;
    @Autowired
    private MarkMapper markMapper;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private UserRepository userRepository;

    public MarkResponse createMark(String login, CreateMarkRequest createMarkRequest) {
        markValidator.validateCreateMarkRequest(createMarkRequest);
        final Optional<User> creator = userRepository.findById(login);
        if (creator.isEmpty()) {
            throw new UserNotFoundException();
        }
        final User user = creator.get();
        final Mark mark = new Mark();
        mark.setTitle(createMarkRequest.getTitle());
        mark.setDescription(createMarkRequest.getDescription());
        mark.setCreator(user.getLogin());
        mark.setType((user.getRole().equals(ADMIN) || user.getRole().equals(MODERATOR)) ? SYSTEM : CUSTOM);
        return markMapper.mapToMarkResponse(markRepository.save(mark));
    }

    public MarkResponse getMark(int id) {
        return markMapper.mapToMarkResponse(markRepository.findById(id)
            .orElseThrow(MarkNotFoundException::new));
    }

    public MarkResponse updateMark(String login, UpdateMarkRequest updateMarkRequest) {
        markValidator.validateUpdateMarkRequest(updateMarkRequest);
        final Optional<Mark> optionalMark = markRepository.findById(updateMarkRequest.getId());
        if (optionalMark.isEmpty()) {
            throw new MarkNotFoundException();
        }
        final Mark mark = optionalMark.get();
        checkAccess(login, mark);
        mark.setTitle(updateMarkRequest.getTitle());
        mark.setDescription(updateMarkRequest.getDescription());
        return markMapper.mapToMarkResponse(markRepository.save(mark));
    }

    private void checkAccess(String login, Mark mark) {
        final Optional<User> creator = userRepository.findById(login);
        if (creator.isEmpty()) {
            throw new UserNotFoundException();
        }
        final User user = creator.get();
        if (mark.getType().equals(SYSTEM) && !user.getRole().equals(ADMIN) && !user.getRole().equals(MODERATOR) ||
            mark.getType().equals(CUSTOM) && !mark.getCreator().equals(user.getLogin())) {
            throw new AccessDeniedException();
        }
    }

    public List<MarkResponse> getMarkList() {
        return markRepository.findAll().stream()
            .map(mark -> markMapper.mapToMarkResponse(mark))
            .collect(Collectors.toList());
    }

    public void deleteMark(int id) {
        if(!markRepository.existsById(id)){
            throw new MarkNotFoundException();
        }
        markRepository.deleteById(id);
    }

}
