package km.hw55.microgram4.dto;

import km.hw55.microgram4.model.Like;
import km.hw55.microgram4.model.Publication;
import km.hw55.microgram4.model.User;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {

    public static LikeDTO from(Like like) {
        return builder()
                .id(like.getId())
                .likeFor(like.getLikeForPublication())
                .likeBy(like.getLikeByUser())
                .date(like.getDate())
                .build();
    }

    private String id;
    private Publication likeFor;
    private User likeBy;
    private LocalDate date;

}
