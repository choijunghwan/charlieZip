package study.charlieZip.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.entity.Address;
import study.charlieZip.entity.Gender;
import study.charlieZip.entity.Member;
import study.charlieZip.repository.MemberJpaRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberJpaRepository memberJpaRepository;

    /**
     * 회원가입
     */
    @Test
    public void join() {

        //given
        Address address = new Address("하남", "미사강변", "12345");
        Member member = Member.builder()
                .username("charlie")
                .password("12345")
                .date("19960105")
                .gender(Gender.MAN)
                .address(address)
                .build();

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberJpaRepository.findById(saveId).get());
    }

    /**
     * 중복_회원_예외
     */
    @Test
    public void validDuplicate() {

        //given
        Address address = new Address("하남", "미사강변", "12345");
        Member member1 = Member.builder()
                .username("charlie")
                .password("12345")
                .date("19960105")
                .gender(Gender.MAN)
                .address(address)
                .build();

        Member member2 = Member.builder()
                .username("charlie")
                .password("123")
                .date("19950405")
                .gender(Gender.MAN)
                .address(address)
                .build();

        //when
        memberService.join(member1);

        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> {
                    memberService.join(member2);
                }
        );
        String message = exception.getMessage();
        assertEquals("이미 존재하는 회원입니다.", message);
    }

}