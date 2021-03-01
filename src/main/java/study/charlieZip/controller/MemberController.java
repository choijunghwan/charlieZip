package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.charlieZip.dto.MemberForm;
import study.charlieZip.entity.Gender;
import study.charlieZip.entity.Member;
import study.charlieZip.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/login")
    public String login() {
        return "members/memberLogin";
    }

    /**
     * 회원가입
     */
    // Get방식을 이용해 화면으로 데이터 MemberForm을 넘김
    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form) {
//        Member member = new Member(form.getUsername(),
//                                    form.getPassword(),
//                                    form.getDate(),
//                                    form.getGender());

        Member member = Member.builder()
                .username(form.getUsername())
                .password(form.getPassword())
                .date(form.getDate())
                .gender(form.getGender())
                .build();

        memberService.join(member);
        return "redirect:/";
    }
}
