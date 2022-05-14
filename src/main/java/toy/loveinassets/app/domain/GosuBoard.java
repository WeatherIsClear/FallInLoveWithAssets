package toy.loveinassets.app.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class GosuBoard {

    @Id
    @GeneratedValue
    @Column(name = "gosu_board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private int views;
}
