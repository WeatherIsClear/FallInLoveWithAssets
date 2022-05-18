package toy.loveinassets.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.loveinassets.app.domain.enums.AssetsType;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Assets {

    @Id
    @GeneratedValue
    @Column(name = "assets_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private BigDecimal amount;

    @Enumerated(STRING)
    private AssetsType assetsType;
}
