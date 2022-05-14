package toy.loveinassets.bank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentication {

    @Id
    @GeneratedValue
    @Column(name = "authentication_id")
    private Long id;

    private String name;
    private String rrn;

    public Authentication(String name, String rrn) {
        this.name = name;
        this.rrn = rrn;
    }
}
