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
import org.springframework.stereotype.Repository;
import toy.loveinassets.app.dto.GosuBoardsResponse;
import toy.loveinassets.app.dto.QGosuBoardsResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.loveinassets.app.domain.QGosuBoard.gosuBoard;
import static toy.loveinassets.app.domain.QMember.member;

@Repository
public class GosuBoardRepositoryImpl implements GosuBoardRepositoryCustom {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public GosuBoardRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<GosuBoardsResponse> gosuBoardList(Pageable pageable) {
        JPAQuery<GosuBoardsResponse> query = queryFactory
                .select(new QGosuBoardsResponse(
                        gosuBoard.id,
                        gosuBoard.member.name,
                        gosuBoard.title,
                        gosuBoard.content,
                        gosuBoard.createdDate
                ))
                .from(gosuBoard)
                .join(gosuBoard.member, member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(gosuBoard.getType(), gosuBoard.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }
        List<GosuBoardsResponse> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
