package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import toy.loveinassets.bank.domain.enums.BankCode;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Bank {

    @Id
    @GeneratedValue
    @Column(name = "bank_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BankCode bankCode;

    public Bank(BankCode bankCode) {
        this.bankCode = bankCode;
    }
}
