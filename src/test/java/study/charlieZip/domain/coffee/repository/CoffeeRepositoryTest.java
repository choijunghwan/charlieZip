package study.charlieZip.domain.coffee.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.coffee.dto.coffeeBoardDto;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.dto.CoffeeSearchCondition;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CoffeeRepositoryTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("페이징 정상 작동")
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
        Page<coffeeBoardDto> page = coffeeRepository.searchPage(condition, pageRequest);

        //then
        List<coffeeBoardDto> content = page.getContent();

        assertThat(content.size()).isEqualTo(8);   // 현재 블록 갯수
        assertThat(page.getTotalElements()).isEqualTo(78);  // 전체 갯수
        assertThat(page.getNumber()).isEqualTo(0);  // 현재 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(10);  // 전체 페이지 갯수
        assertThat(page.isFirst()).isTrue();   //첫 페이지 존재여부
        assertThat(page.hasNext()).isTrue();   //다음 거가 있는지 존재여부

        for (coffeeBoardDto coffeePageDto : content) {
            System.out.println("coffeePageDto = " + coffeePageDto);
        }

    }

    @Test
    @DisplayName("BaseTimeEntity 등록 테스트")
    public void BaseTimeEntity_등록() {
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

        coffeeRepository.save(coffee_board);

        //when
        List<Coffee_Board> boardList = coffeeRepository.findAll();
        Coffee_Board findBoard = boardList.get(0);

        System.out.println(">>>CreatedDate() = " + findBoard.getCreatedDate() + ", modifiedDate = " + findBoard.getLastModifiedDate());

        assertThat(findBoard.getCreatedDate()).isBefore(LocalDateTime.now());
        assertThat(findBoard.getLastModifiedDate()).isBefore(LocalDateTime.now());
    }

    @Test
    @DisplayName("BaseTimeEntity 수정 테스트")
    public void BaseTimeEntity_수정() {
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

        coffeeRepository.save(coffee_board);

        List<Coffee_Board> boardList = coffeeRepository.findAll();
        Coffee_Board findBoard = boardList.get(0);

        //when
        //JPA의 더티체킹을 이용
        findBoard.changeStoreName("빈브라더스");

        em.flush();
        em.clear();


        Coffee_Board board = coffeeRepository.findById(1L).orElseThrow(EntityNotFoundException::new);

        System.out.println(">>>CreatedDate() = " + board.getCreatedDate() + ", modifiedDate = " + board.getLastModifiedDate());

        assertThat(board.getCreatedDate()).isBefore(LocalDateTime.now());
        assertThat(board.getLastModifiedDate()).isAfter(board.getCreatedDate());
    }

}