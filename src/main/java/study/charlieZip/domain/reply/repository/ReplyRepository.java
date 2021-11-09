package study.charlieZip.domain.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.coffee_board= :coffee_board_id")
    List<Reply> findByCoffee_board(@Param("coffee_board_id") Coffee_Board coffee_board);
}
