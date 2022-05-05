package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toy.loveinassets.bank.enums.HistoryType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
