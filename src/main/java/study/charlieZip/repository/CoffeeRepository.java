package study.charlieZip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.entity.Coffee_Board;

public interface CoffeeRepository extends JpaRepository<Coffee_Board, Long> {
}
