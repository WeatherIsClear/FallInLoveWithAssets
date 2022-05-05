package toy.loveinassets;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.authentication.domain.Authentication;
import toy.loveinassets.bank.domain.Bank;
import toy.loveinassets.bank.domain.DepositAccount;
import toy.loveinassets.bank.domain.SavingsAccount;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static java.time.LocalDate.*;
import static toy.loveinassets.bank.domain.enums.BankCode.*;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct

    public void init() {
        initService.initDB();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void initDB() {
            MemberDto memberDtoA = new MemberDto("김동영",
                    "yeondonge@gmail.com",
                    of(1998, 5, 26));

            Member memberA = Member.of(memberDtoA);

            MemberDto memberDtoB = new MemberDto("이선제",
                    "seon@gmail.com",
                    of(1998, 2, 9));

            Member memberB = Member.of(memberDtoB);

            memberA.matchFriend(memberB);
            memberB.matchFriend(memberA);
            memberA.acceptFriend();
            memberB.acceptFriend();

            em.persist(memberA);
            em.persist(memberB);

            Authentication auth = new Authentication("123456-1234567");
            em.persist(auth);

            Bank dongYeongBank = new Bank(DY);
            Bank taeYeongBank = new Bank(TY);
            Bank seonJeBank = new Bank(SJ);
            em.persist(dongYeongBank);
            em.persist(taeYeongBank);
            em.persist(seonJeBank);

            DepositAccount dongYeongAccount = new DepositAccount("12-135-131313", BigDecimal.valueOf(38000000000L), dongYeongBank);
            DepositAccount taeYeongAccount = new DepositAccount("1-23-987654", BigDecimal.valueOf(20L), taeYeongBank);
            DepositAccount seonJeAccount = new DepositAccount("123-13-123456", BigDecimal.valueOf(200000000L), seonJeBank);
            em.persist(dongYeongAccount);
            em.persist(taeYeongAccount);
            em.persist(seonJeAccount);

            SavingsAccount dongYeongSavingsAccount = new SavingsAccount(dongYeongAccount,
                    "21-321-123654",
                    BigDecimal.valueOf(20000000000000L),
                    of(2022, 1, 1), of(2025, 1, 1),
                    25,
                    BigDecimal.valueOf(1000000000L));

            em.persist(dongYeongSavingsAccount);

            em.flush();
            em.clear();
        }
    }
}
