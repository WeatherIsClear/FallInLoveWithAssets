package toy.loveinassets.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.Authentication;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAccessMemberDto {

    private String name;
    private boolean isAuth;
    private Authentication authentication;
}
