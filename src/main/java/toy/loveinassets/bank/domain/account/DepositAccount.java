package toy.loveinassets.bank.domain.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.dto.AccountDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DEPOSIT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositAccount extends Account {

    public DepositAccount(String number, BigDecimal amount, BankMember bankMember) {
        super(number, amount, bankMember);
    }

}