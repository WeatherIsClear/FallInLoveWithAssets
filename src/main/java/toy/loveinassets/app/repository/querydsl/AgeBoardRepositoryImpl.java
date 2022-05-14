package toy.loveinassets.app.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import toy.loveinassets.app.domain.AgeBoard;
import toy.loveinassets.app.domain.enums.AgeGroup;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.loveinassets.app.domain.QAgeBoard.*;
import static toy.loveinassets.app.domain.QMember.*;

public class AgeBoardRepositoryImpl implements AgeBoardRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AgeBoardRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<AgeBoard> ageBoardList(AgeGroup ageGroup, Pageable pageable) {
        List<AgeBoard> list = queryFactory.selectFrom(ageBoard)
                .join(ageBoard.member, member).fetchJoin()
                .where(ageBoard.ageGroup.eq(ageGroup))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(ageBoard.createdDate.desc())
                .fetch();

        return new PageImpl<>(list, pageable, list.size());
    }
}
