package km.hw55.microgram4.controller;

import km.hw55.microgram4.dto.UserDTO;
import km.hw55.microgram4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Page<UserDTO> getUsers(@ApiIgnore Pageable pageable) {
        return userService.getAll(pageable);
    }

//    @GetMapping("/profile/{login}")
//    public String getUserByLogin(@PathVariable("login") String login, Model model) {
//        var u = userRepo.findByUsername(login);
//        model.addAttribute("user", u);
//        return "profile";
//    }
}
