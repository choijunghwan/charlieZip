package study.charlieZip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import study.charlieZip.dto.CoffeeBoardDto;
import study.charlieZip.entity.Coffee_Board;
import study.charlieZip.repository.CoffeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 게시글 등록, 유효성 검사
     */
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
}
