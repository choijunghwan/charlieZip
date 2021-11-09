package study.charlieZip.domain.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.coffee.dto.coffeeBoardDto;
import study.charlieZip.domain.coffee.dto.CoffeeSearchCondition;
import study.charlieZip.domain.coffee.dto.Paging;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.repository.CoffeeRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;


    /**
     * 게시글 작성
     */
    @Transactional
    public Long savePost(Coffee_Board coffee_board) {
        coffeeRepository.save(coffee_board);
        return coffee_board.getId();
    }

    /**
     * 가장 최근 게시글 목록 조회
     */
    public Page<coffeeBoardDto> findPosts() {
        CoffeeSearchCondition condition = new CoffeeSearchCondition(null, null);
        return coffeeRepository.searchPage(condition, PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "id")));
    }

    /**
     * 게시글 페이징 목록 출력
     */
    public Page<coffeeBoardDto> getPostPaging(CoffeeSearchCondition condition, Integer pageNum) {
        return coffeeRepository.searchPage(condition, PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "coffee_board_id")));
    }


    /**
     * 게시글 페이징 번호
     */
    private static final int PAGE_POST_COUNT = 8;       //한 페이지에 존재하는 게시글 수
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 번호수

    public Paging getPageList(CoffeeSearchCondition condition, Integer pageNum) {
        Page<coffeeBoardDto> postPaging = getPostPaging(condition, pageNum);

        Paging paging = new Paging();
        paging.setCoffeeBoardDtoList(postPaging.getContent());

        // 총 게시글 갯수
        long totalElements = postPaging.getTotalElements();

        // 전체 페이지 수
        int totalPages = postPaging.getTotalPages();

        int blockNum = 0;
        // Math.floor 는 버림값  ex) 1.2 -> 1.0
        blockNum = (int) Math.floor((pageNum - 1) / BLOCK_PAGE_NUM_COUNT);
        int blockStartNum = (BLOCK_PAGE_NUM_COUNT * blockNum) + 1;
        int blockLastNum = totalPages <= (blockStartNum + (BLOCK_PAGE_NUM_COUNT - 1)) ? totalPages : (blockStartNum + (BLOCK_PAGE_NUM_COUNT - 1));

        paging.setBlockStartNum(blockStartNum);
        paging.setBlockLastNum(blockLastNum);
        paging.setLastPageNum(totalPages);
        paging.setNowPageNum(pageNum);

        return paging;
    }



    /**
     * 게시글 찾기
     */
    public Coffee_Board findOne(Long coffee_board_id) {
        return coffeeRepository.findById(coffee_board_id).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Long id) {
        coffeeRepository.deleteById(id);
    }

}
