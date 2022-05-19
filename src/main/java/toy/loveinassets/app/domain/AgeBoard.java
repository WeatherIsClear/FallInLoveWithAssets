package toy.loveinassets.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.BaseTimeEntity;
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.BoardRegistrationDto;
import toy.loveinassets.app.dto.BoardUpdateRequest;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AgeBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "age_borad_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "ageBoard", cascade = REMOVE)
    private List<AgeComment> ageComments = new ArrayList<>();

    private String title;

    private String content;

    private int views;

    @Enumerated(STRING)
    private AgeGroup ageGroup;

    @Builder
    private AgeBoard(Member member, String title, String content, int views, AgeGroup ageGroup) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.views = views;
        this.ageGroup = ageGroup;
    }

    public static AgeBoard of(Member member, BoardRegistrationDto request) {
        return AgeBoard.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .views(0)
                .ageGroup(AgeGroup.getAgeGroup(member.getMemberYear()))
                .build();
    }

    public void view() {
        this.views++;
    }

    public void update(BoardUpdateRequest request) {

        writerChecked(request.getMemberId());

        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void writerChecked(Long memberId) {
        if (!memberId.equals(this.getMember().getId())) {
            throw new IllegalStateException("수정할 수 없습니다");
        }
    }
}
