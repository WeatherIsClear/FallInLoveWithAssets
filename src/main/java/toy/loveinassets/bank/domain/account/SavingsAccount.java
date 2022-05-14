package toy.loveinassets.bank.domain.account;

import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.domain.account.DepositAccount;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@DiscriminatorValue("SAVINGS")
@NoArgsConstructor(access = PROTECTED)
public class SavingsAccount extends Account {

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "deposit_account_id")
    private Account depositAccount;
    private LocalDate startDate;

    private LocalDate endDate;

    private int depositDate;

    private BigDecimal depositAmount;

    public SavingsAccount(Account account, String number, BigDecimal amount, BankMember bankMember, LocalDate startDate, LocalDate endDate,
                          int depositDate, BigDecimal depositAmount) {
        super(number, amount, bankMember);
        this.depositAccount = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositDate = depositDate;
        this.depositAmount = depositAmount;
    }

}
