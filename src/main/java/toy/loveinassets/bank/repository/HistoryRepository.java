package toy.loveinassets.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.loveinassets.bank.domain.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
