package toy.loveinassets.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.domain.History;
import toy.loveinassets.bank.domain.account.Account;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.dto.HistoryDto;
import toy.loveinassets.bank.repository.AccountRepository;
import toy.loveinassets.bank.repository.AuthenticationRepository;
import toy.loveinassets.bank.repository.BankMemberRepository;
import toy.loveinassets.bank.repository.HistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final AuthenticationRepository authenticationRepository;
    private final BankMemberRepository bankMemberRepository;
    private final AccountRepository accountRepository;


    @Override
    public List<HistoryDto> getHistoryList(List<AccountDto> accountDtoList) {
        List<HistoryDto> historyDtoList = new ArrayList<>();
        if (!accountDtoList.isEmpty()) {
            for (AccountDto accountDto : accountDtoList) {
                Account findAccount = accountRepository.findByNumber(accountDto.getNumber());
                historyDtoList.addAll(historyRepository.findDepositByToAccount(findAccount)
                        .stream().map(e -> new HistoryDto(e.getToAccount().getBankMember().getBank(), e.getToAccount().getNumber()
                        ,e.getFromAccount().getNumber(), e.getRest_amount(), e.getTradingTime()))
                        .collect(Collectors.toList()));

                historyDtoList.addAll(historyRepository.findWithdrawalByFromAccount(findAccount)
                        .stream().map(e -> new HistoryDto(e.getFromAccount().getBankMember().getBank(), e.getFromAccount().getNumber()
                                ,e.getToAccount().getNumber(), e.getRest_amount(), e.getTradingTime()))
                        .collect(Collectors.toList()));
            }
            return historyDtoList;
        }
        return null;
    }
}
