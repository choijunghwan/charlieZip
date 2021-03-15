package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.service.CoffeeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CoffeeService coffeeService;

    @GetMapping("/")
    public String home(Model model) {
        Page<CoffeePageDto> posts = coffeeService.findPosts();
        List<CoffeePageDto> boardList = posts.getContent();
        model.addAttribute("boardList", boardList);

        return "index";
    }
}
