package km.hw55.microgram4.model;

import km.hw55.microgram4.util.Generator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Data
@Document(collection = "comments")
@CompoundIndex(def = "{'commentator' : 1, 'commentFor' : 1}")
public class Comment {
    private static Random r = new Random();

    @Id
    private String id = UUID.randomUUID().toString();
    @DBRef
    private User commentator;
    @DBRef
    private Publication commentFor;
    private String text;
    private LocalDate date;

    public static Comment make(User commentator, Publication commentFor, LocalDate publicationDate) {
        int t = LocalDate.now().compareTo(publicationDate);
        String text = Generator.makeDescription();
        Comment c = new Comment();
        c.setCommentator(commentator);
        c.setCommentFor(commentFor);
        c.setText(text);
        c.setDate(LocalDate.now().minusDays(r.nextInt(t)+1));
        return c;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentatorLogin='" + commentator.getUsername() + '\'' +
                ", commentFor='" + commentFor.getImage() + '\'' +
                ", text=" + text + '\'' +
                ", date=" + date.toString() +
                '}';
    }
}
