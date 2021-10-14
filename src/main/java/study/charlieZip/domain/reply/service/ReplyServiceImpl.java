package study.charlieZip.domain.reply.service;

import study.charlieZip.domain.reply.entity.Reply;

public interface ReplyServiceImpl {

    public Long saveReply(Reply reply);
    public Reply findOne(Long reply_id);
    public void deleteReply(Long reply_id);

}
