package toy.loveinassets.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.BaseTimeEntity;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.BoardRegistrationDto;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class GosuBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "gosu_board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "gosuBoard")
    private List<GosuComment> gosuComments = new ArrayList<>();

    private String title;

    private String content;

    private int views;

    @Builder
    private GosuBoard(Member member, String title, String content, int views) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.views = views;
    }

    public static GosuBoard of(Member member, BoardRegistrationDto request) {
        return GosuBoard.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .views(0)
                .build();
    }
}
