package toy.loveinassets.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import toy.loveinassets.InitData;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.repository.AuthenticationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
@Slf4j
class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Autowired
    InitData initData;

    @Autowired
    AuthenticationRepository authenticationRepository;

    /**
     * 계좌 가져오기 테스트(성공)
     */
    @Test
    @DisplayName("멤버가 계좌를 가져오면, 인증 여부(true)를 거쳐, BankMember에 자신의 이름, 인증서의 주민번호로 계좌를 가져올 수 있어야 함 ")
    void 계좌_가져오기_테스트() throws Exception
    {
        //given
        initData.initAuth();
        Authentication findAuth = authenticationRepository.findByNameAndRrn("김동영", "980526-1234567");
        //when
        List<AccountDto> accountList = accountService.getAccountList(new AccountAccessMemberDto("김동영", true, findAuth));

        //then
        Assertions.assertThat(accountList.size()).isEqualTo(2);
        log.info("Account [{}]", accountList);
    }


}