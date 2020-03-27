package km.hw55.microgram4.controller;

import km.hw55.microgram4.annotations.ApiPageable;
import km.hw55.microgram4.dto.PublicationDTO;
import km.hw55.microgram4.service.PublicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @ApiPageable
    @GetMapping
    public Slice<PublicationDTO> getAllPublications(@ApiIgnore Pageable pageable) {
        return publicationService.findAllPublications(pageable);
    }

    @GetMapping("/{userId}")
    public Slice<PublicationDTO> getPublicationsByUserId(@ApiIgnore Pageable pageable, @PathVariable("userId") String userId) {
        return publicationService.findAllByUserId(pageable, userId);
    }

    @GetMapping("/")
    public Slice<PublicationDTO> getAuthUserPublications(@ApiIgnore Pageable pageable) {
        return publicationService.findAuthUserPublications(pageable);
    }

    @GetMapping("/other")
    public Slice<PublicationDTO> getOtherUsersPublicationsByUserEmail(@ApiIgnore Pageable pageable) {
        return publicationService.findOtherPublicationsByUserId(pageable);
    }

    @PutMapping("/add")
    public String addPublicationForUser(@RequestParam String image, @RequestParam String descr) {
        return publicationService.addPublication(image, descr);
    }

    @DeleteMapping("/delete/{publicationId}")
    public String deleteUserPublication(@PathVariable("publicationId") String publicationId) {
        return publicationService.deletePublication(publicationId);
    }
}
