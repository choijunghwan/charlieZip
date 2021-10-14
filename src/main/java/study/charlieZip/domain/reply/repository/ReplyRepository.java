package study.charlieZip.domain.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.charlieZip.domain.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
