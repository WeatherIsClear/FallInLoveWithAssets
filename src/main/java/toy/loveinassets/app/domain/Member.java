package toy.loveinassets.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.enums.JoinFriendStatus;
import toy.loveinassets.app.dto.MemberDto;
import toy.loveinassets.bank.domain.Authentication;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;
import static toy.loveinassets.app.domain.enums.JoinFriendStatus.*;

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

    @Enumerated(STRING)
    private JoinFriendStatus status;

    private String name;

    private String email;

    private LocalDate birth;

    private boolean isAuth;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

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

    public void acceptFriend() {
        this.status = ACCEPT;
    }
    public void matchFriend(Member friend) {
        this.status = WAITING;
        this.friend = friend;
    }

    public void completeAuth() {
        this.isAuth = true;
    }
}
