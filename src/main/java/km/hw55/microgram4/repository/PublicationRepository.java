package km.hw55.microgram4.repository;

import km.hw55.microgram4.model.Publication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublicationRepository extends PagingAndSortingRepository<Publication, String> {
    Slice<Publication> findAllByUserId(String userId, Pageable pageable);

    @Query("{'user' : {'$ne' : ?0}}")
    Slice<Publication> findByUserIdNotContains(String userEmail, Pageable pageable);
}
// get publication date
// get publisher
// get publication liker's
