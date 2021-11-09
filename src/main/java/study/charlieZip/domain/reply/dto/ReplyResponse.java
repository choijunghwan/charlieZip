package study.charlieZip.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.charlieZip.domain.reply.entity.Reply;

@Getter
@Setter
public class ReplyResponse {

    private Long cno;
    private Long bno;
    private String writer;
    private String comment;
    private int heartNum;

    @Builder
    public ReplyResponse(Long cno, Long bno, String writer, String comment, int heartNum) {
        this.cno = cno;
        this.bno = bno;
        this.writer = writer;
        this.comment = comment;
        this.heartNum = heartNum;
    }


}
