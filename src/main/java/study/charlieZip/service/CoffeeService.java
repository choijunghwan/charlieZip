package study.charlieZip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.repository.CoffeeRepository;

import java.util.List;

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
     * 게시글 목록 조회
     */
    public List<Coffee_Board> findPosts() {
        return coffeeRepository.findAll();
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

    /**
     * 게시글 수정
     */
    @Transactional
    public void updatePost(Long id, CoffeeBoardDto boardDto) {
        Coffee_Board findPost = coffeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        findPost = Coffee_Board.builder()
                .store_name(boardDto.getStore_name())
                .menu_name(boardDto.getMenu_name())
                .price(boardDto.getPrice())
                .sweet(boardDto.getSweet())
                .acidity(boardDto.getAcidity())
                .body(boardDto.getBody())
                .balance(boardDto.getBalance())
                .aftertaste(boardDto.getAftertaste())
                .aroma(boardDto.getAroma())
                .desc(boardDto.getDesc())
                .build();

    }
}
