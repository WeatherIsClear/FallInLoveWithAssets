package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class SavingsAccount {

    @Id
    @GeneratedValue
    @Column(name = "savings_account_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "deposit_account_id")
    private DepositAccount depositAccount;

    private String number;

    private BigDecimal amount;

    private LocalDate startDate;

    private LocalDate endDate;

    private int depositDate;

    private BigDecimal depositAmount;

    public SavingsAccount(DepositAccount depositAccount, String number, BigDecimal amount, LocalDate startDate, LocalDate endDate, int depositDate, BigDecimal depositAmount) {
        this.depositAccount = depositAccount;
        this.number = number;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositDate = depositDate;
        this.depositAmount = depositAmount;
    }
}
