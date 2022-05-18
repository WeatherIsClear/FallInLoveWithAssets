package toy.loveinassets.app.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddFriend {

    @Id
    @GeneratedValue
    @Column(name = "add_friend_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "from_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Member friend;
}
