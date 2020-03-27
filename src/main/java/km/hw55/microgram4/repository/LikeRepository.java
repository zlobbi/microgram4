package km.hw55.microgram4.repository;

import km.hw55.microgram4.model.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LikeRepository extends PagingAndSortingRepository<Like, String> {

    Slice<Like> findAllByLikeForPublication(String likeFor, Pageable pageable);

    boolean findByLikeByUser_IdAndLikeForPublication_Id(String userId, String publicationId);
}
// get publication date
// get publisher
// get publication liker's
