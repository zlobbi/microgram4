package km.hw55.microgram4.service;

import km.hw55.microgram4.dto.LikeDTO;
import km.hw55.microgram4.repository.LikeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepo;

    public LikeService(LikeRepository likeRepo) {
        this.likeRepo = likeRepo;
    }

    public Slice<LikeDTO> findAllLikes(Pageable pageable) {
        var slice = likeRepo.findAll(pageable);
        return slice.map(LikeDTO::from);
    }

    public Slice<LikeDTO> findAllLikesForPublication(String likeFor, Pageable pageable) {
        var likes = likeRepo.findAllByLikeForPublication(likeFor, pageable);
        return likes.map(LikeDTO::from);
    }
}
