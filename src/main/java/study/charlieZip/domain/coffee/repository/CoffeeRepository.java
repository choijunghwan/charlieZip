package study.charlieZip.domain.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.domain.coffee.entity.Coffee_Board;

public interface CoffeeRepository extends JpaRepository<Coffee_Board, Long>, CoffeeRepositoryCustom {
}
