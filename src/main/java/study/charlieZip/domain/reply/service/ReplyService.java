package study.charlieZip.domain.reply.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.reply.entity.Reply;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ReplyService implements ReplyServiceImpl{

    /**
     * 댓글 작성
     */
    @Override
    public Long saveReply(Reply reply) {
        return null;
    }

    /**
     * 댓글 조회
     */
    @Override
    public Reply findOne(Long reply_id) {
        return null;
    }

    /**
     * 댓글 삭제
     */
    @Override
    public void deleteReply(Long reply_id) {

    }
}
