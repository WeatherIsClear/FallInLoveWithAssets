package toy.loveinassets.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.dto.MemberDto;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
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

    @Builder
    private Member(String name, String email, LocalDate birth, boolean isAuth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.isAuth = isAuth;
    }

    public static Member of(MemberDto dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .isAuth(false)
                .build();
    }

    public void matchFriend(Member friend) {
        this.friend = friend;
    }


}
