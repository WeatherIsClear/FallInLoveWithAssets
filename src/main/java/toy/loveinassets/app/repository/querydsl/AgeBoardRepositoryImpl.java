package toy.loveinassets.app.repository.querydsl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        JPAQuery<AgeBoard> query = queryFactory
                .selectFrom(ageBoard)
                .join(ageBoard.member, member).fetchJoin()
                .where(ageBoard.ageGroup.eq(ageGroup))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(ageBoard.getType(), ageBoard.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }
        List<AgeBoard> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
