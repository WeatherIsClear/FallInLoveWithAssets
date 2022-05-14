package toy.loveinassets.bank.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountDto {
    private String number;
    private BigDecimal amount;
}
