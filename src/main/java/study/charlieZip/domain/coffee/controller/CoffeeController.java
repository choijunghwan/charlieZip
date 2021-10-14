package study.charlieZip.domain.coffee.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.charlieZip.domain.coffee.dto.*;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.service.CoffeeService;
import study.charlieZip.domain.member.service.MemberService;
import study.charlieZip.global.common.GlobalConst;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final MemberService memberService;

    /**
     * 게시글 목록
     */
    @GetMapping("/coffees")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                       @ModelAttribute("coffeeSearch") CoffeeSearchCondition condition) {



        Paging pageList = coffeeService.getPageList(condition, pageNum);
//        model.addAttribute("boardList", boardList);
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

        CoffeeBoardSaveForm coffeeBoardSaveForm = CoffeeBoardSaveForm.builder()
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

        model.addAttribute("coffeeBoardDto", coffeeBoardSaveForm);
        model.addAttribute("writer", writer);
        return "coffees/coffee";
    }

    /**
     * 게시글 등록 폼
     */
    @GetMapping("coffees/new")
    public String write(Model model) {
        model.addAttribute("board", new CoffeeBoardSaveForm());
        return "coffees/addForm";
    }

    /**
     * 게시글 등록
     */
    @PostMapping("coffees/new")
    public String write(@Validated @ModelAttribute("board") CoffeeBoardSaveForm form, BindingResult bindingResult,
                        @SessionAttribute(name = GlobalConst.LOGIN_MEMBER, required = false) Long loginMemberId) {

        if (bindingResult.hasErrors()) {
            log.info("게시글 등록 오류 발생={}",bindingResult);
            return "coffees/addForm";
        }

        Coffee_Board coffee_board = Coffee_Board.builder()
                .store_name(form.getStore_name())
                .menu_name(form.getMenu_name())
                .price(form.getPrice())
                .sweet(form.getSweet())
                .acidity(form.getAcidity())
                .body(form.getBody())
                .balance(form.getBalance())
                .aftertaste(form.getAftertaste())
                .aroma(form.getAroma())
                .desc(form.getDesc())
                .member(memberService.findOne(loginMemberId))
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

        CoffeeBoardUpdateForm coffeeBoardUpdateForm = CoffeeBoardUpdateForm.builder()
                .id(board.getId())
                .member_id(board.getMember().getId())
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

        model.addAttribute("board", coffeeBoardUpdateForm);
        return "coffees/editForm";
    }

    /**
     * 게시글 수정
     */
    @PostMapping(value = "/coffees/{boardId}/edit")
    public String update(@PathVariable("boardId") Long boardId,
                         @Validated @ModelAttribute("board") CoffeeBoardUpdateForm board, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("게시물 수정 에러={}", bindingResult);
            return "coffees/editForm";
        }

        Coffee_Board findPost = Coffee_Board.builder()
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
                .member(memberService.findOne(board.getMember_id()))
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
