package toy.loveinassets.bank.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.repository.AuthenticationRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceImplTest {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthenticationRepository authenticationRepository;

    
    /**
     * 인증 테스트 (성공)
     */
    @Test
    @DisplayName("이름과 주민번호가 BankMember에 존재 시, 인증에 성공해야 함 (인증서 발부)")
    void 인증_성공_테스트() throws Exception
    {
        //given
        String name = "김동영";
        String rrn = "980526-1234567";
        //when
        Boolean authentication = authenticationService.getAuthentication(name, rrn);
        Authentication auth = authenticationRepository.findByNameAndRrn(name, rrn);
        //then
        assertThat(authentication).isTrue();
        assertThat(auth).isNotNull();
    }

    /**
     * 인증 테스트 (실패)
     */
    @Test
    @DisplayName("이름과 주민번호가 동시에 매칭되는 BankMember 미존재 시, 인증에 실패해야 함 (인증서 발부하지 않음")
    void 인증_실패_테스트() throws Exception
    {
        //given
        String name = "이선제";
        String rrn = "985026-1234567";
        //when
        Boolean authentication = authenticationService.getAuthentication(name, rrn);
        Authentication auth = authenticationRepository.findByNameAndRrn(name, rrn);
        //then
        assertThat(authentication).isFalse();
        assertThat(auth).isNull();
    }

        



}