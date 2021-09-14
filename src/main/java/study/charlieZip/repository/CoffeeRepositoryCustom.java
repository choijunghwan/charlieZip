package study.charlieZip.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.dto.CoffeeSearchCondition;

public interface CoffeeRepositoryCustom {
    Page<CoffeePageDto> searchPage(CoffeeSearchCondition condition, Pageable pageable);
}
