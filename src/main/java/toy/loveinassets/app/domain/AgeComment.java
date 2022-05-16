package toy.loveinassets.app.domain;

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
public class AgeComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "age_comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "age_board_id")
    private AgeBoard ageBoard;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private AgeComment parent;

    @OneToMany(mappedBy = "parent")
    private List<AgeComment> children = new ArrayList<>();

    private String content;

    @Builder
    private AgeComment(Member member, AgeBoard ageBoard, String content) {
        this.member = member;
        this.ageBoard = ageBoard;
        this.content = content;
    }

    public static AgeComment of(Member member, AgeBoard ageBoard, String content) {
        return AgeComment.builder()
                .member(member)
                .ageBoard(ageBoard)
                .content(content)
                .build();
    }

    public void addComment(AgeComment parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }
}
