package study.charlieZip.domain.reply.service;

import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.reply.dto.ReplyResponse;
import study.charlieZip.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyService {

    //댓글 작성
    public Long saveReply(Long boardId, String content, String writer);

    public List<ReplyResponse> replyList(Long boardId);

    //댓글 삭제
    public void deleteReply(Long id);

    //댓글 수정
    public Long update(Long id, String content);

}
