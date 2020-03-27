package km.hw55.microgram4.service;

import km.hw55.microgram4.dto.PublicationDTO;
import km.hw55.microgram4.model.Publication;
import km.hw55.microgram4.model.User;
import km.hw55.microgram4.repository.PublicationRepository;
import km.hw55.microgram4.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepo;
    private final UserRepository userRepo;

    public PublicationService(PublicationRepository publicationRepo, UserRepository userRepo) {
        this.publicationRepo = publicationRepo;
        this.userRepo = userRepo;
    }

    public Slice<PublicationDTO> findAllPublications(Pageable pageable) {
        var slice = publicationRepo.findAll(pageable);
        return slice.map(PublicationDTO::from);
    }

    public Slice<PublicationDTO> findAllByUserId(Pageable pageable, String userId) {
        var slice = publicationRepo.findAllByUserId(userId, pageable);
        return slice.map(PublicationDTO::from);
    }

    public Slice<PublicationDTO> findAuthUserPublications(Pageable pageable) {
        var slice = publicationRepo.findAllByUserId(getUser().getId(), pageable);
        return slice.map(PublicationDTO::from);
    }

    public Slice<PublicationDTO> findOtherPublicationsByUserId(Pageable pageable) {
        var slice = publicationRepo.findByUserIdNotContains(getUser().getId(), pageable);
        return slice.map(PublicationDTO::from);
    }

    public String addPublication(String image, String descr) {
        User u = getUser();
        Publication p = Publication.make(image, descr, u);
        publicationRepo.save(p);
        System.out.println("Here");
        u.plusPublication();
        userRepo.save(u);
        return "Publication added";
    }

    public User getUser() {
        // get current authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUsername(auth.getName()).get();
    }

}
