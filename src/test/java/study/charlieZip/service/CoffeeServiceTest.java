package study.charlieZip.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.repository.CoffeeRepository;
import study.charlieZip.domain.coffee.service.CoffeeService;
import study.charlieZip.domain.member.entity.Gender;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.member.service.MemberService;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CoffeeServiceTest {

    @Autowired
    CoffeeService coffeeService;
    @Autowired
    MemberService memberService;


    /**
     * 게시글 등록
     */
    @Test
    public void savePost() {

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
        System.out.println("coffeeService = " + coffeeService.findOne(saveId));


    }

    /**
     * sweet, acidity, ...   0~60 범위 벗어나는거 에러나는지 테스트
     */


}