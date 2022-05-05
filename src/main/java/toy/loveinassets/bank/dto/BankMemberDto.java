package toy.loveinassets.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.Bank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankMemberDto {

    private String name;
    private Bank bank;
    private String rrn;
}
