package km.hw55.microgram4.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Data
@Document(collection = "likes")
@CompoundIndex(def = "{'likeByUser' : 1, 'publication' : 1}")
public class Like {
    private static Random r = new Random();

    @Id
    private String id = UUID.randomUUID().toString();
    @DBRef
    private Publication likeForPublication;
    @DBRef
    private User likeByUser;
    private LocalDate date;


    public static Like make(User likeByUser, Publication likeForPublication, LocalDate likeForDate) {
        int t = LocalDate.now().compareTo(likeForDate);
        Like l = new Like();
        l.setLikeByUser(likeByUser);
        l.setLikeForPublication(likeForPublication);
        l.setDate(LocalDate.now().minusDays(r.nextInt(t)+1));
        return l;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", liker login='" + likeByUser.getUsername() + '\'' +
                ", likeFor='" + likeForPublication.getImage() + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}
