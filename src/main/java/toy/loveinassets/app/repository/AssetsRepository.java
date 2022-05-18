package toy.loveinassets.app.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.app.domain.Assets;

public interface AssetsRepository extends JpaRepository<Assets, Long> {
}
