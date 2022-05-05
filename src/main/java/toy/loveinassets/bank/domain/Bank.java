package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.enums.BankCode;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank {

    @Id
    @GeneratedValue
    @Column(name = "bank_id")
    private Long id;

    private String bankName;

    @Enumerated(value = EnumType.STRING)
    private BankCode bankCode;
}
