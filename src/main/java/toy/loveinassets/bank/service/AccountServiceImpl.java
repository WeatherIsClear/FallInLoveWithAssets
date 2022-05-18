package toy.loveinassets.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.repository.AccountRepository;
import toy.loveinassets.bank.repository.AuthenticationRepository;
import toy.loveinassets.bank.repository.BankMemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AuthenticationRepository authenticationRepository;
    private final BankMemberRepository bankMemberRepository;

    @Override
    public List<AccountDto> getAccountList(AccountAccessMemberDto accountAccessMemberDto) {
        if (accountAccessMemberDto.isAuth()) {
            BankMember findBankMember = bankMemberRepository.findByNameAndRrn(accountAccessMemberDto.getName(), accountAccessMemberDto.getAuthentication().getRrn());
            return findBankMember.getAccountList().stream()
                    .map(e -> new AccountDto(e.getNumber(), e.getAmount()))
                    .collect(Collectors.toList());

        }
        return null;
    }
}
