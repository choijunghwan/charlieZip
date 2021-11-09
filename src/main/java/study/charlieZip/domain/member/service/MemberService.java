package study.charlieZip.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.member.dto.MemberUpdateForm;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.member.repository.MemberJpaRepository;
import study.charlieZip.domain.member.repository.MemberRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService { //implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

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
     * 회원 정보 수정
     */
    @Transactional
    public Long update(Long memberId, MemberUpdateForm memberForm) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member findMember = memberOptional.orElse(null);
        if (findMember == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }

        //더피 체킹 이용
        findMember.changeMember(memberForm.getUsername(), memberForm.getPassword(), memberForm.getDate(), memberForm.getGender());

        return findMember.getId();
    }


    /**
     * Spring Security를 이용해 로그인할때 권한을 부여해주는것인데.
     * 이 프로젝트에서는 "vkdlxj3562"이라는 아이디만 ADMIN 권한을 가지게 하였다.
     * @return 사용자의 (아이디, 비밀번호, 권한)
     * @throws UsernameNotFoundException
     */
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Member> userEntityWrapper = memberJpaRepository.findByUsername(username);
        Member userEntity = userEntityWrapper.get(0);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("vkdlxj3562").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }*/


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
        return memberJpaRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }

}
