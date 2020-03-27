package km.hw55.microgram4.model;

import km.hw55.microgram4.util.Generator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Data
@Document(collection = "publications")
public class Publication {
    @Id
    private String id = UUID.randomUUID().toString();
    @DBRef
    @Indexed
    private User user;
    private String image;
    private String description;
    private LocalDate date;
    private int likesCount;
    private int commentsCount;

    private static Random r = new Random();

    public static Publication make(User user) {
        Publication p = new Publication();
        String image = Generator.makeName();
        p.setImage(image.toLowerCase() + ".jpg");
        p.setUser(user);
        p.setDescription(Generator.makeDescription());
        p.setDate(LocalDate.now().minusDays(r.nextInt(20)+2));
        return p;
    }

    public static Publication make(String image, String descr, User user) {
        Publication p = new Publication();
        p.setImage(image);
        p.setDescription(descr);
        p.setUser(user);
        p.setDate(LocalDate.now());
        return p;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + id + '\'' +
                ", userLogin='" + user.getUsername() + '\'' +
                ", image='" + image + '\'' +
                ", likes='" + likesCount + '\'' +
                ", comments='" + commentsCount + '\'' +
                ", description=" + description + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public void plusLike() {
        this.likesCount++;
    }
    public void minusLike() { this.likesCount--; }

    public void plusComment() { this.commentsCount++; }

    public void minusComment() { this.commentsCount--; }
}
