package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.service.CoffeeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    /**
     * 게시글 목록
     */
    @GetMapping("/coffee/list")
    public String list(Model model) {
        List<Coffee_Board> boardList = coffeeService.findPosts();
        model.addAttribute("boardList", boardList);
        return "coffee/coffeeList";
    }

    /**
     * 게시글 상세페이지
     */
    @GetMapping("coffee/view/{boardId}")
    public String view(@PathVariable("boardId") Long boardId, Model model) {
        Coffee_Board board = coffeeService.findOne(boardId);

        CoffeeBoardDto coffeeBoardDto = CoffeeBoardDto.builder()
                .id(board.getId())
                .store_name(board.getStore_name())
                .menu_name(board.getMenu_name())
                .price(board.getPrice())
                .desc(board.getDesc())
                .build();

        model.addAttribute("coffeeBoardDto", coffeeBoardDto);
        return "coffee/coffeeView";
    }

    /**
     * 게시글 등록
     */
    @GetMapping("coffee/new")
    public String write(Model model) {
        model.addAttribute("board", new CoffeeBoardDto());
        return "coffee/createCoffeeForm";
    }

    @PostMapping("coffee/new")
    public String write(CoffeeBoardDto coffeeBoardDto) {
        Coffee_Board coffee_board = Coffee_Board.builder()
                .store_name(coffeeBoardDto.getStore_name())
                .menu_name(coffeeBoardDto.getMenu_name())
                .price(coffeeBoardDto.getPrice())
                .sweet(coffeeBoardDto.getSweet())
                .acidity(coffeeBoardDto.getAcidity())
                .body(coffeeBoardDto.getBody())
                .balance(coffeeBoardDto.getBalance())
                .aftertaste(coffeeBoardDto.getAftertaste())
                .aroma(coffeeBoardDto.getAroma())
                .desc(coffeeBoardDto.getDesc())
                .build();

        coffeeService.savePost(coffee_board);
        return "redirect:/coffee/list";
    }

    /**
     * 게시물 수정
     */
    @GetMapping("coffee/{boardId}/edit")
    public String update(@PathVariable("boardId") Long boardId, Model model) {
        Coffee_Board board = coffeeService.findOne(boardId);

        CoffeeBoardDto coffeeBoardDto = CoffeeBoardDto.builder()
                .id(board.getId())
                .store_name(board.getStore_name())
                .menu_name(board.getMenu_name())
                .price(board.getPrice())
                .sweet(board.getSweet())
                .acidity(board.getAcidity())
                .body(board.getBody())
                .balance(board.getBalance())
                .aftertaste(board.getAftertaste())
                .aroma(board.getAroma())
                .desc(board.getDesc())
                .build();

        model.addAttribute("board", coffeeBoardDto);
        return "coffee/createCoffeeForm";
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/coffee/{boardId}")
    public String delete(@PathVariable("boardId") Long boardId) {
        coffeeService.deletePost(boardId);

        return "redirect:/coffee/list";
    }
}