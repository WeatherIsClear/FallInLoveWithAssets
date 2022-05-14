package toy.loveinassets.bank.service;

import org.springframework.stereotype.Service;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;

import java.util.List;

@Service
public interface AccountService {

    List<AccountDto> getAccountList(AccountAccessMemberDto accountAccessMemberDto);
}
