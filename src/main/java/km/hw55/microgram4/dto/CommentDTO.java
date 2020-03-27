package km.hw55.microgram4.dto;

import km.hw55.microgram4.model.Comment;
import km.hw55.microgram4.model.Publication;
import km.hw55.microgram4.model.User;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDTO {

    public static CommentDTO from(Comment comment) {
        return builder()
                .id(comment.getId())
                .commentator(comment.getCommentator())
                .commentFor(comment.getCommentFor())
                .text(comment.getText())
                .date(comment.getDate())
                .build();
    }

    private String id;
    private User commentator;
    private Publication commentFor;
    private String text;
    private LocalDate date;

}
