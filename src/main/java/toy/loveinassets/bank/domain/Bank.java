package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.enums.BankCode;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank {

    @Id
    @GeneratedValue
    @Column(name = "bank_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

    @Enumerated(value = EnumType.STRING)
    private BankCode bankCode;

    public Bank(BankCode bankCode) {
        this.bankCode = bankCode;
    }
}
