package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import study.charlieZip.GlobalConst;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.entity.Member;
import study.charlieZip.service.CoffeeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CoffeeService coffeeService;

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = GlobalConst.LOGIN_MEMBER, required = false) Long loginMemberId,
            Model model) {

        Page<CoffeePageDto> posts = coffeeService.findPosts();
        List<CoffeePageDto> boardList = posts.getContent();
        model.addAttribute("boardList", boardList);


        //세션에 회원 데이터가 없으면
        if (loginMemberId == null) {
            return "index";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("memberId", loginMemberId);
        return "loginHome";
    }
}
