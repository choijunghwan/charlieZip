package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.entity.Gender;
import study.charlieZip.entity.Member;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class initDB {

    private final InitDBService initDBService;

    @PostConstruct
    public void init() {
        initDBService.init();
    }

    @Component
    static class InitDBService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Member member1 = Member.builder()
                    .username("vkdlxj3562")
                    .password(passwordEncoder.encode("1234"))
                    .date("19960105")
                    .gender(Gender.MAN)
                    .build();

            Coffee_Board coffee_board1 = Coffee_Board.builder()
                    .store_name("벙커컴퍼니")
                    .menu_name("하프앤하프")
                    .price(7000)
                    .sweet(50)
                    .acidity(10)
                    .body(50)
                    .balance(50)
                    .aftertaste(50)
                    .aroma(10)
                    .desc("식물성 크림과 동물성크림을 섞어 만든 달달한 아인슈페너 느낌")
                    .build();

            Coffee_Board coffee_board2 = Coffee_Board.builder()
                    .store_name("벙커컴퍼니")
                    .menu_name("쥬시롱블랙")
                    .price(5500)
                    .sweet(35)
                    .acidity(30)
                    .body(50)
                    .balance(50)
                    .aftertaste(50)
                    .aroma(45)
                    .desc("묵직한 바디감이 느껴지고 다크하지만 중간에 적절한 베리한 신맛이 느껴진다.")
                    .build();

            Coffee_Board coffee_board3 = Coffee_Board.builder()
                    .store_name("커피몽타주")
                    .menu_name("아메리카노")
                    .price(4000)
                    .sweet(30)
                    .acidity(20)
                    .body(40)
                    .balance(50)
                    .aftertaste(40)
                    .aroma(40)
                    .desc("아직 안마셔봄 ㅋㅋ")
                    .build();

            Coffee_Board coffee_board4 = Coffee_Board.builder()
                    .store_name("이디야")
                    .menu_name("아메리카노")
                    .price(3300)
                    .sweet(30)
                    .acidity(10)
                    .body(30)
                    .balance(30)
                    .aftertaste(20)
                    .aroma(20)
                    .desc("에스프레소 농도가 연하다.")
                    .build();

//            for (int i = 0; i < 40; i++) {
//                Coffee_Board coffee_board = Coffee_Board.builder()
//                        .store_name("벙커컴퍼니" + i)
//                        .menu_name("하프앤하프")
//                        .price(7000)
//                        .sweet(50)
//                        .acidity(10)
//                        .body(50)
//                        .balance(50)
//                        .aftertaste(30)
//                        .aroma(20)
//                        .desc("식물성 크림과 동물성크림을 섞어 만든 달달한 아인슈페너 느낌")
//                        .build();
//                em.persist(coffee_board);
//            }
            em.persist(member1);
            em.persist(coffee_board1);
            em.persist(coffee_board2);
            em.persist(coffee_board3);
            em.persist(coffee_board4);
        }
    }
}
