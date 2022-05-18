package toy.loveinassets.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.repository.AssetsRepository;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.repository.AccountRepository;
import toy.loveinassets.bank.service.AccountService;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class CustomAccountProxy {
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final AssetsRepository assetsRepository;
    private final AccountService accountService;


    List<AccountDto> checkAccount(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        return accountService.getAccountList(new AccountAccessMemberDto(findMember.getName(), findMember.isAuth(), findMember.getAuthentication()));
    }


}
