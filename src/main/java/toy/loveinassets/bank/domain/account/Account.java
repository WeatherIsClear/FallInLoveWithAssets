package toy.loveinassets.bank.domain.account;

import toy.loveinassets.bank.domain.BankMember;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String number;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "bank_member_id")
    private BankMember bankMember;

    public Account(String number, BigDecimal amount, BankMember bankMember) {
        this.number = number;
        this.amount = amount;
        this.bankMember = bankMember;
    }
}
