package study.charlieZip.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.member.entity.Address;
import study.charlieZip.domain.member.entity.Gender;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.member.repository.MemberJpaRepository;
import study.charlieZip.domain.member.repository.MemberRepository;
import study.charlieZip.domain.member.service.MemberService;


import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;


    /**
     * 회원가입
     */
    @Test
    public void join() {

        //given
        Address address = new Address("하남", "미사강변", "12345");
        Member member = Member.builder()
                .username("test")
                .password("12345")
                .date("19960105")
                .gender(Gender.MAN)
                .address(address)
                .build();

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberService.findOne(saveId));
    }

    /**
     * 중복_회원_예외
     */
    @Test
    @Transactional(rollbackFor = {IllegalStateException.class})
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

    @Test
    @DisplayName("회원 정보 수정")
    public void memberUpdate() {
        //given
        Member member1 = Member.builder()
                .username("charlie")
                .password("12345")
                .date("19960105")
                .gender(Gender.MAN)
                .build();

        memberService.join(member1);

        //when
        Member findMember = memberService.findOne(1L);

        findMember.changeMember("vkdlxj3562", "12345", "19960105", Gender.MAN);

        em.flush();
        em.clear();

        //then
        Member updateMember = memberService.findOne(1L);

        Assertions.assertThat(updateMember.getUsername()).isEqualTo("vkdlxj3562");

    }

}