package study.charlieZip.domain.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.coffee.service.CoffeeService;
import study.charlieZip.domain.reply.dto.ReplyResponse;
import study.charlieZip.domain.reply.entity.Reply;
import study.charlieZip.domain.reply.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final CoffeeService coffeeService;

    /**
     * 댓글 저장
     */
    @Transactional
    @Override
    public Long saveReply(Long boardId, String content, String writer) {
        Coffee_Board findBoard = coffeeService.findOne(boardId);
        Reply reply = Reply.builder()
                .writer(writer)
                .comment(content)
                .heartNum(0)
                .coffee_board(findBoard)
                .build();

        replyRepository.save(reply);
        return reply.getId();
    }

    /**
     * 댓글 리스트
     */
    @Override
    public List<ReplyResponse> replyList(Long boardId) {
        Coffee_Board findBoard = coffeeService.findOne(boardId);
        List<Reply> findReplys = replyRepository.findByCoffee_board(findBoard);


        List<ReplyResponse> replyRes = new ArrayList<>();
        for (Reply findReply : findReplys) {
            ReplyResponse replyResponse = ReplyResponse.builder()
                    .cno(findReply.getId())
                    .bno(findReply.getCoffee_board().getId())
                    .writer(findReply.getWriter())
                    .comment(findReply.getComment())
                    .heartNum(findReply.getHeartNum())
                    .build();
            replyRes.add(replyResponse);
        }

        return replyRes;
    }


    /**
     * 댓글 삭제
     */
    @Transactional
    @Override
    public void deleteReply(Long reply_id) {
        replyRepository.deleteById(reply_id);
    }


    /**
     * 댓글 수정
     */
    @Transactional
    @Override
    public Long update(Long reply_id, String content) {
        Optional<Reply> replyOptional = replyRepository.findById(reply_id);
        Reply findReply = replyOptional.orElse(null);
        if (findReply == null) {
            throw new IllegalStateException("존재하지 않는 댓글입니다.");
        }

        // 더티체킹을 이용해 수정
        findReply.changeReply(content);

        return findReply.getId();
    }
}
