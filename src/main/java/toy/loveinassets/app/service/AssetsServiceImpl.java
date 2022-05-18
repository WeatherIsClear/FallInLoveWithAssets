package toy.loveinassets.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.repository.AssetsRepository;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.dto.AccountDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {
    private final AssetsRepository assetsRepository;
    private final MemberRepository memberRepository;
    private final CustomAccountProxy customAccountProxy;


    @Override
    public List<AccountDto> getAccount(Long memberId) {
        List<AccountDto> accountList = customAccountProxy.checkAccount(memberId);

        for (AccountDto account : accountList) {

        }
    }
}
