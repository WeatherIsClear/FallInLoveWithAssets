package toy.loveinassets.app.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "friend_id")
    private Member friend;

    private String name;

    private String email;

    private LocalDate birth;

    private boolean isAuth;
}
