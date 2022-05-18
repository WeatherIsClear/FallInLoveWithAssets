package toy.loveinassets.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.dto.AccountDto;

import java.util.List;

public interface AssetsService {

    List<AccountDto> getAccount(Long memberId);

}
