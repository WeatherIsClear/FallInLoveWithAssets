package toy.loveinassets.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.loveinassets.app.domain.Member;
import toy.loveinassets.app.repository.MemberRepository;
import toy.loveinassets.bank.domain.Authentication;
import toy.loveinassets.bank.domain.BankMember;
import toy.loveinassets.bank.repository.AuthenticationRepository;
import toy.loveinassets.bank.repository.BankMemberRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BankMemberRepository bankMemberRepository;
    private final AuthenticationRepository authenticationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Boolean getAuthentication(String name, String rrn) {

        if(bankMemberRepository.existsBankMemberByNameAndRrn(name, rrn)) {
            authenticationRepository.save(new Authentication(name, rrn));
            Member findMember = memberRepository.findByName(name);
            findMember.completeAuth();
            return true;
        }
        return false;
    }
}
