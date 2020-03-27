package km.hw55.microgram4.model;

import km.hw55.microgram4.util.Generator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Document(collection = "users")
@Data
public class User implements UserDetails {

    @Id
    private String id = UUID.randomUUID().toString();
    private String username;
    private String email;
    private String password;
    private String pass;
    private int publicationsCount;
    private int subscribersCount;
    private int subscribtionsCount;

    public static User make() {
        User u = new User();
        String name = Generator.makeName().toLowerCase();
        u.setUsername(name);
        u.setEmail(Generator.makeEmail() + ".com");
        u.setPass(Generator.makePassword());
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPass()));
        return u;
    }

    public static User make(String username, String email, String pass) {
        User u = new User();
        u.setUsername(username);
        u.setPass(pass);
        u.setEmail(email);
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPass()));
        return u;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + pass + '\'' +
                ", subscribers='" + subscribersCount + '\'' +
                ", subscribtions='" + subscribtionsCount + '\'' +
                ", publications=" + publicationsCount +
                '}';
    }

    // Publications actions -----------------------------
    public void plusPublication() { this.publicationsCount++; }

    public void minusPublication() { this.publicationsCount--;}

    public int getPublicationsCoutn() { return this.publicationsCount; }

    // Subscribtions actions -----------------------------

    public void plusSubscribtionsCount() {
        this.subscribtionsCount++;
    }

    public void minusSubscribtionsCount() {
        this.subscribtionsCount--;
    }

    public int getSubscribtionsCount() {
        return this.subscribtionsCount;
    }


    // Subscribers actions -----------------------------

    public void plusSubscribersCount() {
        this.subscribersCount++;
    }

    public void minusSubscribersCount() {
        this.subscribersCount--;
    }

    public int getSubscribersCount() {
        return this.subscribersCount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("FULL"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
