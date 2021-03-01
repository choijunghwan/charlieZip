package study.charlieZip.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.repository.CoffeeRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CoffeeServiceTest {

    @Autowired
    CoffeeService coffeeService;
    @Autowired
    CoffeeRepository coffeeRepository;
    /**
     * 게시글 등록
     */
    @Test
    public void savePost() {

        //given
        Coffee_Board coffee_board = Coffee_Board.builder()
                .store_name("벙커컴퍼니")
                .menu_name("아메리카노")
                .price(5500)
                .sweet(30)
                .acidity(20)
                .aftertaste(40)
                .aroma(10)
                .balance(60)
                .body(20)
                .count(1)
                .desc("맛있다.")
                .build();

        //when
        Long saveId = coffeeService.savePost(coffee_board);

        //then
        assertEquals(coffee_board, coffeeRepository.findById(saveId).get());
    }

    /**
     * sweet, acidity, ...   0~60 범위 벗어나는거 에러나는지 테스트
     */


    /**
     *
     */
}