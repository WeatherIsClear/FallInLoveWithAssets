package toy.loveinassets.bank.domain.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.dto.AccountDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String number;
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_member_id")
    private BankMember bankMember;

    public Account(String number, BigDecimal amount, BankMember bankMember) {
        this.number = number;
        this.amount = amount;
        this.bankMember = bankMember;
    }


    public void addAccount(BankMember bankMember) {
        this.bankMember = bankMember;
        bankMember.getAccountList().add(this);
    }

    @Builder
    private Account(String number, BigDecimal amount) {
        this.number = number;
        this.amount = amount;
    }

    public static Account of(AccountDto accountDto) {
        return Account.builder()
                .number(accountDto.getNumber())
                .amount(accountDto.getAmount())
                .build();
    }
}
