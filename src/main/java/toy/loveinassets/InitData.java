package toy.loveinassets;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.AgeComment;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.AgeBoardRegistrationDto;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.Bank;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.domain.History;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.domain.account.DepositAccount;
import toy.loveinassets.bank.domain.account.SavingsAccount;
import toy.loveinassets.bank.domain.enums.HistoryType;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.dto.BankMemberDto;
import toy.loveinassets.bank.repository.AccountRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDate.*;
import static toy.loveinassets.bank.domain.enums.BankCode.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void initMember() {
        initService.initDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        @Autowired
        private final AccountRepository accountRepository;

        public void initDB() {
            log.info("================ DATA INIT START ================");
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
            DepositAccount dongYeongAccount = new DepositAccount("12-135-131313", BigDecimal.valueOf(100000L), bankMember1);
            dongYeongAccount.addAccount(bankMember1);
            DepositAccount taeYeongAccount = new DepositAccount("1-23-987654", BigDecimal.valueOf(200000L), bankMember2);
            taeYeongAccount.addAccount(bankMember2);
            DepositAccount seonJeAccount = new DepositAccount("123-13-123456", BigDecimal.valueOf(300000L), bankMember3);
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

            History history1 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(99000L), HistoryType.WITHDRAWAL, seonJeAccount, dongYeongAccount);
            History history2 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(110000L), HistoryType.DEPOSIT, seonJeAccount, dongYeongAccount);
            History history3 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(98000L), HistoryType.WITHDRAWAL, seonJeAccount, dongYeongAccount);
            History history4 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(120000L), HistoryType.DEPOSIT, seonJeAccount, dongYeongAccount);
            History history5 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(97000L), HistoryType.WITHDRAWAL, seonJeAccount, dongYeongAccount);
            History history6 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(130000L), HistoryType.DEPOSIT, seonJeAccount, dongYeongAccount);
            History history7 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(96000L), HistoryType.WITHDRAWAL, seonJeAccount, dongYeongAccount);
            History history8 = new History(BigDecimal.valueOf(1000L), BigDecimal.valueOf(140000L), HistoryType.DEPOSIT, seonJeAccount, dongYeongAccount);

            seonJeAccount.deposit(BigDecimal.valueOf(4000L));
            dongYeongAccount.withdrawal(BigDecimal.valueOf(4000L));
            em.persist(history1);
            em.persist(history2);
            em.persist(history3);
            em.persist(history4);
            em.persist(history5);
            em.persist(history6);
            em.persist(history7);
            em.persist(history8);

            Authentication auth1 = new Authentication("김동영", "980526-1234567");
            Authentication auth2 = new Authentication("김태영", "970805-1234567");
            Authentication auth3 = new Authentication("이선제", "980209-1234567");

            em.persist(auth1);
            em.persist(auth2);
            em.persist(auth3);

            AgeBoard detail = AgeBoard.of(memberA, new AgeBoardRegistrationDto(1L, "detail", "details"));
            em.persist(detail);
            AgeComment parent = AgeComment.of(memberB, detail, "content");
            em.persist(parent);
            AgeComment of = AgeComment.of(memberA, detail, "content");
            of.addComment(parent);
            em.persist(of);

            em.flush();
            em.clear();
            log.info("================ DATA INIT END ================");
        }
    }
}
