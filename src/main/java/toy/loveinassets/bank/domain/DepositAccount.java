package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositAccount {

    @Id
    @GeneratedValue
    @Column(name = "deposit_account_id")
    private Long id;

    private String number;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public DepositAccount(String number, BigDecimal amount, Bank bank) {
        this.number = number;
        this.amount = amount;
        this.bank = bank;
    }
}