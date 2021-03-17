package study.charlieZip.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {

    private final static int BLOCK_PAGE_NUM_COUNT = 5;  //블럭에 존재하는 페이지 번호수
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    private int nowPageNum = 0;
}
