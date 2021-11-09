package study.charlieZip.domain.coffee.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Paging {

    private final static int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 번호수
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    private int nowPageNum = 0;

    private List<coffeeBoardDto> coffeeBoardDtoList = new ArrayList<>();

}
