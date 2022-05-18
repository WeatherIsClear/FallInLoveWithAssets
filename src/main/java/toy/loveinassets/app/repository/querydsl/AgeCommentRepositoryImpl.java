package toy.loveinassets.app.repository.querydsl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import toy.loveinassets.app.domain.QAgeComment;
import toy.loveinassets.app.dto.AgeCommentResponse;
import toy.loveinassets.app.dto.QAgeCommentResponse;

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
    public Page<AgeCommentResponse> ageComments(Long ageBoardId, Pageable pageable) {
        return getAgeComments(ageBoardId, null, pageable);
    }

    @Override
    public Page<AgeCommentResponse> nestedComments(Long parentId, Pageable pageable) {
        return getAgeComments(null, parentId, pageable);
    }

    private Page<AgeCommentResponse> getAgeComments(Long ageBoardId, Long parentId, Pageable pageable) {
        System.out.println(ageComment.parent == null);
        JPAQuery<AgeCommentResponse> query = queryFactory
                .select(new QAgeCommentResponse(
                        ageComment.id,
                        ageComment.member.id,
                        ageComment.member.name,
                        ageComment.createdDate,
                        ageComment.content,
                        ageComment.children.size()
                ))
                .from(ageComment)
                .join(ageComment.member, member)
                .where(ageBoardEq(ageBoardId, parentId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(ageComment.getType(), ageComment.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }
        List<AgeCommentResponse> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }

    private BooleanExpression ageBoardEq(Long ageBoardId, Long parentId) {
        return parentId == null ?
                ageComment.ageBoard.id.eq(ageBoardId).and(ageComment.parent.isNull()) :
                ageComment.parent.id.eq(parentId);
    }
}
