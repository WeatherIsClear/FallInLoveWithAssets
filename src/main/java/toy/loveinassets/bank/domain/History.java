package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.domain.account.DepositAccount;
import toy.loveinassets.bank.domain.enums.HistoryType;
import toy.loveinassets.bank.dto.AccountDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "to_id")
    private Account toAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private Account fromAccount;

    private LocalDateTime tradingTime;

    public History(BigDecimal amount, BigDecimal rest_amount, HistoryType historyType, Account toAccount, Account fromAccount) {
        this.amount = amount;
        this.rest_amount = rest_amount;
        this.historyType = historyType;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.tradingTime = LocalDateTime.now();
    }
}
