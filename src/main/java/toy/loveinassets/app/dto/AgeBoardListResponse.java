package toy.loveinassets.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgeBoardListResponse {

    private String memberName;

    private String title;

    private String content;

    private LocalDateTime time;
}
