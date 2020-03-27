package km.hw55.microgram4.controller;
//
import km.hw55.microgram4.annotations.ApiPageable;
import km.hw55.microgram4.dto.LikeDTO;
import km.hw55.microgram4.service.LikeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ApiPageable
    @GetMapping
    public Slice<LikeDTO> getAllLikes(@ApiIgnore Pageable pageable) {
        return likeService.findAllLikes(pageable);
    }

    @GetMapping("/{likeFor}")
    public Slice<LikeDTO> getLikesForPublication(@PathVariable("likeFor") String likeFor, @ApiIgnore Pageable pageable) {
        return likeService.findAllLikesForPublication(likeFor, pageable);
    }

    @GetMapping("/like/{byId}/{forId}")
    public String doLike(@PathVariable("byId") String by, @PathVariable("forId") String forId) {
//        if(!likeRepo.findByLikeByUser_IdAndLikeForPublication_Id(by, forId)) {
            // action
//        }
        // than redirect to publication view
        return "";
    }
}
