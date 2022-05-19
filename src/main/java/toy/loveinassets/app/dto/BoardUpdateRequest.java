package toy.loveinassets.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {

    private Long memberId;

    private Long ageBoardId;

    private String title;

    private String content;
}
