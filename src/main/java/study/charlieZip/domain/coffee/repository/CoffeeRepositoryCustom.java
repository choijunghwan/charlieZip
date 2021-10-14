package study.charlieZip.domain.coffee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.charlieZip.domain.coffee.dto.coffeeBoardDto;
import study.charlieZip.domain.coffee.dto.CoffeeSearchCondition;

public interface CoffeeRepositoryCustom {
    Page<coffeeBoardDto> searchPage(CoffeeSearchCondition condition, Pageable pageable);
}
