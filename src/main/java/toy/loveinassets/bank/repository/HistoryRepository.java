package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.loveinassets.bank.domain.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
