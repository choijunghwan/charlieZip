package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.charlieZip.dto.MemberForm;
import study.charlieZip.entity.Gender;
import study.charlieZip.entity.Member;
import study.charlieZip.service.MemberService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 페이지
     */
    @GetMapping(value = "/members/login")
    public String login() {
        return "members/memberLogin";
    }

    /**
     * 접근 거부 페이지
     */
    @GetMapping(value = "/members/denied")
    public String Denied() {
        return "members/denied";
    }

    /**
     * 회원가입 페이지 폼
     */
    // Get방식을 이용해 화면으로 데이터 MemberForm을 넘김
    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * 회원가입
     */
    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm memberform, Errors errors, Model model) {
        if (errors.hasErrors()) {
            //회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("memberForm", memberform);

            // 유효성 통과 못한 필드와 메시지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "members/createMemberForm";
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = Member.builder()
                .username(memberform.getUsername())
                .password(passwordEncoder.encode(memberform.getPassword()))
                .date(memberform.getDate())
                .gender(memberform.getGender())
                .build();

        memberService.join(member);
        return "redirect:/";
    }
}
