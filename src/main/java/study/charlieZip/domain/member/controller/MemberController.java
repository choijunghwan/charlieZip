package study.charlieZip.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.charlieZip.domain.member.dto.MemberSaveForm;
import study.charlieZip.domain.member.dto.MemberUpdateForm;
import study.charlieZip.domain.member.entity.Gender;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.member.service.MemberService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입 페이지 폼
     */
    // Get방식을 이용해 화면으로 데이터 MemberForm을 넘김
    @GetMapping(value = "/members/new")
    public String addForm(Model model) {
        model.addAttribute("memberForm", new MemberSaveForm());
        Gender[] genders = Gender.values();
        model.addAttribute("genders", genders);
        return "members/addForm";
    }

    /**
     * 회원가입
     */
    @PostMapping(value = "/members/new")
    public String addMember(@Validated @ModelAttribute("memberForm") MemberSaveForm memberForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생={}", bindingResult);
            //회원가입 실패시, 입력 데이터를 유지
            Gender[] genders = Gender.values();
            model.addAttribute("genders", genders);

            return "members/addForm";
        }


        Member member = Member.builder()
                .username(memberForm.getUsername())
                .password(memberForm.getPassword())
                .date(memberForm.getDate())
                .gender(memberForm.getGender())
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

        MemberUpdateForm memberForm = new MemberUpdateForm();
        memberForm.setId(memberId);
        memberForm.setUsername(member.getUsername());
        memberForm.setPassword(member.getPassword());
        memberForm.setDate(member.getDate());
        memberForm.setGender(member.getGender());

        model.addAttribute("member", memberForm);
        return "members/member";
    }

    /**
     * 회원 정보 수정 폼
     */
    @GetMapping("/members/{memberId}/edit")
    public String editForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);
        log.info("회원 정보 수정 {}", member);

        Gender[] genders = Gender.values();
        model.addAttribute("genders", genders);

        MemberUpdateForm memberForm = new MemberUpdateForm();
        memberForm.setId(memberId);
        memberForm.setUsername(member.getUsername());
        memberForm.setPassword(member.getPassword());
        memberForm.setDate(member.getDate());
        memberForm.setGender(member.getGender());

        model.addAttribute("memberForm", memberForm);
        return "members/editForm";
    }

    /**
     * 회원 정보 수정(미완성)
     */
    @PostMapping("/members/{memberId}/edit")
    public String edit(@PathVariable("memberId") Long memberId, Model model,
                       @ModelAttribute("memberForm") MemberUpdateForm memberForm, BindingResult bindingResult) {


        //회원가입 실패시, 입력 데이터를 유지
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생={}", bindingResult);

            Gender[] genders = Gender.values();
            model.addAttribute("genders", genders);
            return "members/editForm";
        }

        Member member = memberService.findOne(memberId);
        member = Member.builder()
                .username(memberForm.getUsername())
                .password(memberForm.getPassword())
                .date(memberForm.getDate())
                .gender(memberForm.getGender())
                .build();

        memberService.update(member);
        return "redirect:/members/{memberId}";
    }
}
