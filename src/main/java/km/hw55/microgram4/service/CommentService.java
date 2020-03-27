package km.hw55.microgram4.service;

import km.hw55.microgram4.dto.CommentDTO;
import km.hw55.microgram4.repository.CommentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepo;

    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Slice<CommentDTO> findAllComments(Pageable pageable) {
        var slice = commentRepo.findAll(pageable);
        return slice.map(CommentDTO::from);
    }

    public Slice<CommentDTO> findAllCommentsForPublication(String id, Pageable pageable) {
        var comments = commentRepo.findAllByCommentForId(id, pageable);
        return comments.map(CommentDTO::from);
    }
}
