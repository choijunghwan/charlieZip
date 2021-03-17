package study.charlieZip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.dto.CoffeeSearchCondition;
import study.charlieZip.entity.Coffee_Board;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee_Board, Long>, CoffeeRepositoryCustom {
}
