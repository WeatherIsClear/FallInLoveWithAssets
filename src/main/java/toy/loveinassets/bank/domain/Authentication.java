package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentication {

    @Id
    @GeneratedValue
    @Column(name = "authentication_id")
    private Long id;

    private String rrn;

    public Authentication(String rrn) {
        this.rrn = rrn;
    }
}
