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
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.dto.GosuCommentResponse;
import toy.loveinassets.app.dto.QAgeCommentResponse;
import toy.loveinassets.app.dto.QGosuCommentResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.loveinassets.app.domain.QAgeComment.ageComment;
import static toy.loveinassets.app.domain.QGosuComment.gosuComment;
import static toy.loveinassets.app.domain.QMember.member;

public class GosuCommentRepositoryImpl implements GosuCommentRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public GosuCommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<GosuCommentResponse> gosuComments(Long gosuBoardId, Pageable pageable) {
        JPAQuery<GosuCommentResponse> query = queryFactory
                .select(new QGosuCommentResponse(
                        gosuComment.id,
                        gosuComment.member.id,
                        gosuComment.member.name,
                        gosuComment.createdDate,
                        gosuComment.content,
                        gosuComment.children.size()
                ))
                .from(gosuComment)
                .join(gosuComment.member, member)
                .where(gosuComment.gosuBoard.id.eq(gosuBoardId).and(gosuComment.parent.isNull()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(gosuComment.getType(), gosuComment.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }
        List<GosuCommentResponse> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
