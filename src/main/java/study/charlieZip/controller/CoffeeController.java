package study.charlieZip.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.dto.CoffeeSearchCondition;
import study.charlieZip.dto.Paging;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.service.CoffeeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;


    /**
     * 게시글 목록
     */
    @GetMapping("/coffees")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, @ModelAttribute("coffeeSearch") CoffeeSearchCondition condition) {
//        List<Coffee_Board> boardList = coffeeService.findPosts();
//        model.addAttribute("boardList", boardList);

        Page<CoffeePageDto> postPaging = coffeeService.getPostPaging(condition, pageNum);
        // 페이징한 게시글 목록들을 찾고
        List<CoffeePageDto> boardList = postPaging.getContent();
        model.addAttribute("boardList", boardList);

        Paging pageList = coffeeService.getPageList(condition, pageNum);
        model.addAttribute("pageList", pageList);


        return "coffees/coffees";
    }

    /**
     * 게시글 상세페이지
     */
    @GetMapping("coffees/{boardId}")
    public String view(@PathVariable("boardId") Long boardId, Model model) {
        Coffee_Board board = coffeeService.findOne(boardId);
        String writer = board.getCreatedBy();

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

        model.addAttribute("coffeeBoardDto", coffeeBoardDto);
        model.addAttribute("writer", writer);
        return "coffees/coffee";
    }

    /**
     * 게시글 등록 폼
     */
    @GetMapping("coffees/new")
    public String write(Model model) {
        model.addAttribute("board", new CoffeeBoardDto());
        return "coffees/addForm";
    }

    /**
     * 게시글 등록
     */
    @PostMapping("coffees/new")
    public String write(@Valid CoffeeBoardDto coffeeBoardDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 게시글 작성 실패시, 데이터 유지
            model.addAttribute("board", coffeeBoardDto);

            // 유효성 통과 못한 필드와 메시지 핸들링
            Map<String, String> validtorResult = coffeeService.validateHandling(errors);
            for (String key : validtorResult.keySet()) {
                model.addAttribute(key, validtorResult.get(key));
            }

            return "coffees/addForm";
        }

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
        return "redirect:/coffees";
    }

    /**
     * 게시물 수정 폼
     */
    @GetMapping("coffees/{boardId}/edit")
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
        return "coffees/editForm";
    }

    /**
     * 게시글 수정
     */
    @PostMapping(value = "/coffees/{boardId}/edit")
    public String update(@PathVariable("boardId") Long boardId, @ModelAttribute CoffeeBoardDto board, RedirectAttributes redirectAttributes) {
        Coffee_Board findPost = coffeeService.findOne(boardId);

        findPost = Coffee_Board.builder()
                .id(boardId)
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
        coffeeService.savePost(findPost);

        redirectAttributes.addAttribute("boardId", boardId);

        return "redirect:/coffees/{boardId}";
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/coffees/{boardId}/delete")
    public String delete(@PathVariable("boardId") Long boardId) {
        coffeeService.deletePost(boardId);

        return "redirect:/coffees";
    }

}
