package toy.loveinassets.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import toy.loveinassets.bank.domain.Bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoryDto {
    private Bank bank;
    private String toAccountNumber;
    private String fromAccountNumber;
    private BigDecimal restAmount;
    private LocalDateTime localDateTime;
}
