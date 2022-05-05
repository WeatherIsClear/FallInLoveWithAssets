package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.bank.dto.BankMemberDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankMember {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private String rrn;

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
