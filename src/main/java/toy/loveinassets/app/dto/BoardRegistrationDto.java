package toy.loveinassets.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegistrationDto {

    private Long memberId;

    private String title;

    private String content;
}
