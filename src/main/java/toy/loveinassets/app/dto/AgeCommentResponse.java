package toy.loveinassets.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgeCommentResponse {

    private Long ageCommentId;

    private Long memberId;

    private LocalDateTime date;

    private String content;

    private int childComment;
}
