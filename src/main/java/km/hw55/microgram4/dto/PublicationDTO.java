package km.hw55.microgram4.dto;

import km.hw55.microgram4.model.Publication;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    public static PublicationDTO from(Publication publication) {
        return builder()
                .id(publication.getId())
                .user(publication.getUser().getUsername())
                .image(publication.getImage())
                .description(publication.getDescription())
                .date(publication.getDate())
                .likesCount(publication.getLikesCount())
                .commentsCount(publication.getCommentsCount())
                .build();
    }

    private String id;
    private String user;
    private String image;
    private String description;
    private LocalDate date;
    private int likesCount;
    private int commentsCount;

}
