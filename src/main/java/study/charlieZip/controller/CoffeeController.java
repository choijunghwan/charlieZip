package study.charlieZip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoffeeController {

    @GetMapping("/coffee/list")
    public String list() {
        return "coffee/coffeeList";
    }

    @GetMapping("coffee/view")
    public String view() {
        return "coffee/coffeeView";
    }

    @GetMapping("coffee/form")
    public String write() {
        return "coffee/createCoffeeForm";
    }
}
