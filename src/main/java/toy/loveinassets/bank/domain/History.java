package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.account.DepositAccount;
import toy.loveinassets.bank.domain.enums.HistoryType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue
    @Column(name = "history_id")
    private Long id;
    private BigDecimal amount;
    private BigDecimal rest_amount;

    @Enumerated(value = EnumType.STRING)
    private HistoryType historyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private DepositAccount account;

}
