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
import toy.loveinassets.app.domain.AgeComment;

import javax.persistence.EntityManager;

import java.util.List;

import static toy.loveinassets.app.domain.QAgeComment.*;
import static toy.loveinassets.app.domain.QMember.*;

public class AgeCommentRepositoryImpl implements AgeCommentRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AgeCommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<AgeComment> ageComments(Long ageBoardId, Pageable pageable) {
        JPAQuery<AgeComment> query = queryFactory
                .selectFrom(ageComment)
                .join(ageComment.member, member).fetchJoin()
                .where(ageComment.ageBoard.id.eq(ageBoardId).and(ageComment.parent.isNull()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(ageComment.getType(), ageComment.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }

        List<AgeComment> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
