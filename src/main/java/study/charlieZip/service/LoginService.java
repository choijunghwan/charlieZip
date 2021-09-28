package study.charlieZip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.charlieZip.entity.Member;
import study.charlieZip.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByUsername(loginId)
                .stream().filter(m -> m.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}
