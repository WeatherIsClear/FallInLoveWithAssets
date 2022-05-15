package toy.loveinassets.bank.service;

import org.springframework.stereotype.Service;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.History;
import toy.loveinassets.bank.dto.AccountAccessMemberDto;
import toy.loveinassets.bank.dto.AccountDto;
import toy.loveinassets.bank.dto.HistoryDto;

import java.util.List;

public interface HistoryService {
    List<HistoryDto> getHistoryList(List<AccountDto> accountDtoList);
}
