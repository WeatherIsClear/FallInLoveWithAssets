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
import toy.loveinassets.app.domain.enums.AgeGroup;
import toy.loveinassets.app.dto.AgeBoardListResponse;
import toy.loveinassets.app.dto.QAgeBoardListResponse;

import javax.persistence.EntityManager;
import java.util.List;

import static toy.loveinassets.app.domain.QAgeBoard.*;

public class AgeBoardRepositoryImpl implements AgeBoardRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AgeBoardRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<AgeBoardListResponse> ageBoardList(AgeGroup ageGroup, Pageable pageable) {

        JPAQuery<AgeBoardListResponse> query = queryFactory
                .select(new QAgeBoardListResponse(ageBoard.member.name, ageBoard.title, ageBoard.content, ageBoard.createdDate))
                .from(ageBoard)
                .where(ageBoard.ageGroup.eq(ageGroup))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(ageBoard.getType(), ageBoard.getMetadata());
            query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(order.getProperty())));
        }
        List<AgeBoardListResponse> result = query.fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
