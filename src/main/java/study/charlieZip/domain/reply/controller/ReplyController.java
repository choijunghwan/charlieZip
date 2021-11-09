package study.charlieZip.domain.reply.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.reply.dto.ReplyResponse;
import study.charlieZip.domain.reply.service.ReplyService;
import study.charlieZip.global.common.GlobalConst;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 리스트
     */
    @GetMapping
    public List<ReplyResponse> replyList(@RequestParam("bno") Long boardId){
        log.info("댓글 리스트 출력 : 게시글 번호 {}", boardId);
        return replyService.replyList(boardId);
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/new")
    public int addComment(@RequestParam("bno") Long boardId, @RequestParam("comment_content") String content,
                          @SessionAttribute(name = GlobalConst.LOGIN_MEMBER, required = false) Member loginMember) {
        Long replyId = replyService.saveReply(boardId, content, loginMember.getUsername());
        return replyId > 0 ? 1 : -1;
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{cno}/edit")
    public int editComment(@PathVariable Long cno, @RequestParam("content") String content) {
        Long replyId = replyService.update(cno, content);
        return replyId > 0 ? 1 : -1;
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{cno}/delete")
    public void deleteComment(@PathVariable Long cno) {
        replyService.deleteReply(cno);
    }
}
