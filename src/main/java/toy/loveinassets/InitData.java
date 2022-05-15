package toy.loveinassets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.AgeBoardRegistrationDto;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.Bank;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.domain.account.DepositAccount;
import toy.loveinassets.bank.domain.account.SavingsAccount;
import toy.loveinassets.bank.dto.BankMemberDto;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDate.*;
import static toy.loveinassets.bank.domain.enums.BankCode.*;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void initMember() {
        initService.initDB();
    }

    public void initAuth() {
        initService.setAuthentication();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void initDB() {
            MemberDto memberDtoA = new MemberDto("김동영",
                    "yeondonge@gmail.com",
                    of(1998, 5, 26), "980526-1234567");

            Member memberA = Member.of(memberDtoA);

            MemberDto memberDtoB = new MemberDto("이선제",
                    "seon@gmail.com",
                    of(1998, 2, 9), "980209-1234567");

            Member memberB = Member.of(memberDtoB);

            memberA.matchFriend(memberB);
            memberB.matchFriend(memberA);
            memberA.acceptFriend();
            memberB.acceptFriend();

            em.persist(memberA);
            em.persist(memberB);


            Bank dongYeongBank = new Bank(DY);
            Bank taeYeongBank = new Bank(TY);
            Bank seonJeBank = new Bank(SJ);
            em.persist(dongYeongBank);
            em.persist(taeYeongBank);
            em.persist(seonJeBank);


            BankMember bankMember1 = BankMember.of(new BankMemberDto("김동영", dongYeongBank, "980526-1234567"));
            BankMember bankMember2 = BankMember.of(new BankMemberDto("김태영", taeYeongBank, "970805-1234567"));
            BankMember bankMember3 = BankMember.of(new BankMemberDto("이선제", seonJeBank, "980209-1234567"));

            em.persist(bankMember1);
            em.persist(bankMember2);
            em.persist(bankMember3);

            //예금 동태선 하나씩, 적금 동영 하나만 하자
            DepositAccount dongYeongAccount = new DepositAccount("12-135-131313", BigDecimal.valueOf(38000000000L), bankMember1);
            dongYeongAccount.addAccount(bankMember1);
            DepositAccount taeYeongAccount = new DepositAccount("1-23-987654", BigDecimal.valueOf(20L), bankMember2);
            taeYeongAccount.addAccount(bankMember2);
            DepositAccount seonJeAccount = new DepositAccount("123-13-123456", BigDecimal.valueOf(200000000L), bankMember3);
            seonJeAccount.addAccount(bankMember3);

            em.persist(dongYeongAccount);
            em.persist(taeYeongAccount);
            em.persist(seonJeAccount);

            SavingsAccount dongYeongSavingsAccount = new SavingsAccount(dongYeongAccount, "21-123-1234", BigDecimal.valueOf(20000000000000L), bankMember1
                    , LocalDate.of(2022, 1, 1),
                    LocalDate.of(2025, 1, 1), 25, BigDecimal.valueOf(100000000L));

            dongYeongSavingsAccount.addAccount(bankMember1);

            for (int i = 0; i < 50; i++) {
                em.persist(AgeBoard.of(
                        memberA, new AgeBoardRegistrationDto(1L, i + "", "content")));
            }

            em.flush();
            em.clear();
        }

        public void setAuthentication() {
            Authentication auth1 = new Authentication("김동영", "980526-1234567");
            Authentication auth2 = new Authentication("김태영", "970805-1234567");
            Authentication auth3 = new Authentication("이선제", "980209-1234567");

            em.persist(auth1);
            em.persist(auth2);
            em.persist(auth3);

            em.flush();
            em.clear();
        }
    }
}
