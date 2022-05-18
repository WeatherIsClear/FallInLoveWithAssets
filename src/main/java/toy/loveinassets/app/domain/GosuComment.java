package toy.loveinassets.app.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.BaseTimeEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class GosuComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "gosu_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private GosuComment parent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "godu_board_id")
    private GosuBoard gosuBoard;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "parent")
    private List<GosuComment> children = new ArrayList<>();



    private String content;

    @Builder
    private GosuComment(Member member, GosuBoard gosuBoard, String content) {
        this.member = member;
        this.gosuBoard = gosuBoard;
        this.content = content;
    }

    public static GosuComment of(Member member, GosuBoard gosuBoard, String content) {
        return GosuComment.builder()
                .member(member)
                .gosuBoard(gosuBoard)
                .content(content)
                .build();
    }

    public void addComment(GosuComment parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
