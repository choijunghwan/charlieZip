package study.charlieZip.domain.reply.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.repository.CoffeeRepository;
import study.charlieZip.domain.reply.entity.Reply;

import javax.persistence.EntityManager;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@DataJpaTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("댓글 데이터 저장")
    void save() {

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

        coffeeRepository.save(coffee_board);

        Reply reply = Reply.builder()
                .writer("vkdlxj3562")
                .comment("졸잼")
                .coffee_board(coffee_board)
                .build();


        replyRepository.save(reply);

        //when
        Reply findReply = replyRepository.findAll().get(0);

        //then
        assertThat(findReply.getWriter()).isEqualTo("vkdlxj3562");

    }

    @Test
    @DisplayName("댓글 리스트 출력")
    void replyList() {

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

        coffeeRepository.save(coffee_board);

        Reply reply1 = Reply.builder()
                .writer("vkdlxj3562")
                .comment("졸잼")
                .coffee_board(coffee_board)
                .build();

        Reply reply2 = Reply.builder()
                .writer("vkdlxj3562")
                .comment("노잼")
                .coffee_board(coffee_board)
                .build();

        replyRepository.save(reply1);
        replyRepository.save(reply2);

        em.flush();
        em.clear();

        //when
        List<Reply> findReplys = replyRepository.findByCoffee_board(coffee_board);

        //then
        assertThat(findReplys.size()).isEqualTo(2);
        for (Reply findReply : findReplys) {
            System.out.println("findReply = " + findReply);
        }

    }
}