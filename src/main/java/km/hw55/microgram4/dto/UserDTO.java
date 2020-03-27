package km.hw55.microgram4.dto;

import km.hw55.microgram4.model.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .pass(user.getPass())
                .publicationsCount(user.getPublicationsCount())
                .subscribersCount(user.getSubscribersCount())
                .subscribtionsCount(user.getSubscribtionsCount())
                .build();
    }

    private String id;
    private String username;
    private String email;
    private String password;
    private String pass;
    private int publicationsCount;
    private int subscribersCount;
    private int subscribtionsCount;

}
