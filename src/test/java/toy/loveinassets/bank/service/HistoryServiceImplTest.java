package toy.loveinassets.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.InitData;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.dto.HistoryDto;
import toy.loveinassets.bank.repository.AuthenticationRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
class HistoryServiceImplTest {

    @Autowired
    HistoryService historyService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    InitData initData;

    /**
     * 히스토리 내역 조회(성공)
     */
    @Test
    @DisplayName("동영 예금 계좌의 히스토리 내역을 조회")
    void 히스토리_전체_조회() throws Exception
    {
        //given
        Authentication findAuth = authenticationRepository.findByNameAndRrn("김동영", "980526-1234567");
        List<AccountDto> accountList = accountService.getAccountList(new AccountAccessMemberDto("김동영", true, findAuth));
        //when
        List<HistoryDto> historyList = historyService.getTotalHistoryList(accountList);

        //then
        assertThat(historyList.size()).isEqualTo(8);
        for (HistoryDto historyDto : historyList) {
            log.info("historyDto = {}", historyDto);
        }
    }
    
    /**
     * 히스토리 입금 내역 조회(성공)
     */
    @Test
    @DisplayName("동영 예금 계좌의 출금 히스토리 내역을 조회")
    void 히스토리_입금_조회() throws Exception
    {
        //given
        Authentication findAuth = authenticationRepository.findByNameAndRrn("김동영", "980526-1234567");
        List<AccountDto> accountList = accountService.getAccountList(new AccountAccessMemberDto("김동영", true, findAuth));
        //when
        List<HistoryDto> historyList = historyService.getDepositHistoryList(accountList);

        //then
        assertThat(historyList.size()).isEqualTo(0);
        for (HistoryDto historyDto : historyList) {
            log.info("historyDto = {}", historyDto);
        }
    }
    /**
     * 히스토리 출금 내역 조회(성공)
     */
    @Test
    @DisplayName("동영 예금 계좌의 입금 히스토리 내역을 조회")
    void 히스토리_출금_조회() throws Exception
    {
        //given
        Authentication findAuth = authenticationRepository.findByNameAndRrn("김동영", "980526-1234567");
        List<AccountDto> accountList = accountService.getAccountList(new AccountAccessMemberDto("김동영", true, findAuth));
        //when
        List<HistoryDto> historyList = historyService.getWithdrawalHistoryList(accountList);

        //then
        assertThat(historyList.size()).isEqualTo(8);
        for (HistoryDto historyDto : historyList) {
            log.info("historyDto = {}", historyDto);
        }
    }
        


}