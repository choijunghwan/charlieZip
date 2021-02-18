package study.charlieZip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.entity.Member;
import study.charlieZip.repository.MemberJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    /**
     * 회원가입
     */
    @Transactional  //데이터 변경이 필요할때
    public Long join(Member member) {
        validateDuplicateMember(member);  //중복 회원 검증
        memberJpaRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberJpaRepository.findByUsername(member.getUsername());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberJpaRepository.findAll();
    }


    /**
     * 회원 찾기
     */
    public Member findOne(Long memberId) {
        /*
        Optional 에서 값 바로 받는 방법
        Optional<Member> result = memberJpaRepository.findById(memberId);

        if (!result.isPresent()) {
            throw new IllegalArgumentException();
        }

        return result.get();
         */

        // 위의 코드를 orElseThrow 사용을 통해 한줄로 줄일 것
        return memberJpaRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }

}
