package toy.loveinassets.bank.domain.account;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.BankMember;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DEPOSIT")
public class DepositAccount extends Account {

    public DepositAccount(String number, BigDecimal amount, BankMember bankMember) {
        super(number, amount, bankMember);
    }

}