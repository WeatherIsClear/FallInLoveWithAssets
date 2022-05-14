package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.dto.BankMemberDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankMember {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private String rrn;

    @OneToMany(mappedBy = "bankMember")
    List<Account> accountList = new ArrayList<>();

    @Builder
    private BankMember(String name, Bank bank, String rrn) {
        this.name = name;
        this.bank = bank;
        this.rrn = rrn;
    }

    public static BankMember of(BankMemberDto dto) {
        return BankMember.builder()
                .name(dto.getName())
                .bank(dto.getBank())
                .rrn(dto.getRrn())
                .build();
    }
}
