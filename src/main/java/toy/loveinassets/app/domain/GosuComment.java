package toy.loveinassets.app.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class GosuComment {

    @Id
    @GeneratedValue
    @Column(name = "gosu_comment_id")
    private Long id;

    private Long parentId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sodu_board_id")
    private GosuBoard gosuBoard;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
}
