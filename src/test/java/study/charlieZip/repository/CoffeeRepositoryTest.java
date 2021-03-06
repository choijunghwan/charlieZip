package study.charlieZip.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.dto.CoffeeSearchCondition;
import study.charlieZip.entity.Coffee_Board;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CoffeeRepositoryTest {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    EntityManager em;

    @Test
    public void paging() {
        //given
        for (int i = 0; i < 78; i++) {
            Coffee_Board coffee_board = Coffee_Board.builder()
                    .store_name("벙커컴퍼니" + i)
                    .menu_name("하프앤하프")
                    .price(7000)
                    .sweet(50)
                    .acidity(10)
                    .body(50)
                    .balance(50)
                    .aftertaste(30)
                    .aroma(20)
                    .desc("식물성 크림과 동물성크림을 섞어 만든 달달한 아인슈페너 느낌")
                    .build();
            em.persist(coffee_board);
        }

        PageRequest pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "id"));
        CoffeeSearchCondition condition = new CoffeeSearchCondition(null, null);
        //when
        Page<CoffeePageDto> page = coffeeRepository.searchPage(condition, pageRequest);

        //then
        List<CoffeePageDto> content = page.getContent();

        assertThat(content.size()).isEqualTo(8);   // 현재 블록 갯수
        assertThat(page.getTotalElements()).isEqualTo(78);  // 전체 갯수
        assertThat(page.getNumber()).isEqualTo(0);  // 현재 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(10);  // 전체 페이지 갯수
        assertThat(page.isFirst()).isTrue();   //첫 페이지 존재여부
        assertThat(page.hasNext()).isTrue();   //다음 거가 있는지 존재여부

        for (CoffeePageDto coffeePageDto : content) {
            System.out.println("coffeePageDto = " + coffeePageDto);
        }

    }

}