package toy.loveinassets.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AgeComment {

    @Id
    @GeneratedValue
    @Column(name = "age_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AgeComment parent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "age_board_id")
    private AgeBoard ageBoard;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
}
