package km.hw55.microgram4.service;

import km.hw55.microgram4.dto.UserDTO;
import km.hw55.microgram4.model.User;
import km.hw55.microgram4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<UserDTO> getAll(Pageable pageable) {
        var slice = userRepository.findAll(pageable);
        return slice.map(UserDTO::from);
    }

    public String registerUser(String username, String email, String pass) {
        String msg = "";
        if(userRepository.existsByUsernameAndEmail(username, email)) {
            msg = "user already registered";
        } else {
            var u = User.make(username, email, pass);
            userRepository.save(u);
            msg = "user registered";
        }
        return msg;
    }
}
