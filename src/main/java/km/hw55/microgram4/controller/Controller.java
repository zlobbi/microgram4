package km.hw55.microgram4.controller;

import km.hw55.microgram4.repository.UserRepository;
import km.hw55.microgram4.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final UserRepository userRepo;
    private final UserService userService;

    public Controller(UserRepository userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @RequestMapping
    public String rootHandler() {
        return "go to path: \\register and add Query Params";
    }

    @PutMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String pass) {
        return userService.registerUser(username, email, pass);
    }

}
