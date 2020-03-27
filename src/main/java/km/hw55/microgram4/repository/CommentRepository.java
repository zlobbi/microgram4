package km.hw55.microgram4.repository;

import km.hw55.microgram4.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
    Slice<Comment> findAllByCommentForId(String commentForId, Pageable pageable);
}
// get publication date
// get publisher
// get publication liker's
