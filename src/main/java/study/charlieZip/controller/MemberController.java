package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.charlieZip.dto.MemberForm;
import study.charlieZip.entity.Gender;
import study.charlieZip.entity.Member;
import study.charlieZip.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 페이지
     */
    @GetMapping(value = "/login")
    public String login() {
        return "login/login";
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
    public String addForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        Gender[] genders = Gender.values();
        model.addAttribute("genders", genders);
        return "members/addForm";
    }

    /**
     * 회원가입
     */
    @PostMapping(value = "/members/new")
    public String addMember(@Valid MemberForm memberform, Errors errors, Model model) {
        if (errors.hasErrors()) {
            //회원가입 실패시, 입력 데이터를 유지
            model.addAttribute("memberForm", memberform);

            // 유효성 통과 못한 필드와 메시지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "members/addForm";
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

    /**
     * 회원 정보 조회
     */
    @GetMapping("/members/{memberId}")
    public String member(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("member", member);
        return "members/member";
    }

    /**
     * 회원 정보 수정 폼
     */
    @GetMapping("/members/{memberId}/edit")
    public String editForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("member", member);
        return "members/editForm";
    }

    /**
     * 회원 정보 수정
     */
    @PostMapping("/members/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, @ModelAttribute MemberForm memberForm) {

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = Member.builder()
                .username(memberForm.getUsername())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .date(memberForm.getDate())
                .gender(memberForm.getGender())
                .build();

        memberService.join(member);
        return "redirect:/members/{memberId}";
    }
}
