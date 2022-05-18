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

    @OneToMany(mappedBy = "ageBoard")
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
}
