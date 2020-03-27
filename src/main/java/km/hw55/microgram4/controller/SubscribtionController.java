package km.hw55.microgram4.controller;

import km.hw55.microgram4.repository.PublicationRepository;
import km.hw55.microgram4.repository.SubscribtionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@org.springframework.stereotype.Controller
public class SubscribtionController {
    @Autowired
    PublicationRepository publicationRepo;

    @Autowired
    SubscribtionRepository subscribtionRepo;

    @GetMapping("/uifollow/{userId}")
    public String getPublicationsByUserId(@PathVariable("userId") String userId, Model model) {
//        List<Subscribtion> subs = subscribtionRepo.findAllBySubscriber_Id(userId);
//        List<Publication> publ = new ArrayList<>();
//        for(Subscribtion s : subs) {
//            publ = StreamSupport.stream(publicationRepo.findAllByUserId(s.getSubscribiant().getId()).spliterator(), true )
//                    .collect(Collectors.toList());
//        }
//        model.addAttribute("publications", publ);
        return "uifollow";
    }
}
