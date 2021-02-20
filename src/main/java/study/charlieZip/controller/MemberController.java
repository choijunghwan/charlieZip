package study.charlieZip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {

    @GetMapping(value = "/members/login")
    public String login() {
        return "members/memberLogin";
    }

    /**
     * 회원가입
     */
    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        return "members/createMemberForm";
    }
}
