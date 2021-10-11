package study.charlieZip.domain.reply.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.charlieZip.domain.coffee.entity.Coffee_Board;
import study.charlieZip.domain.model.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String writer;
    private String comment;
    private int heartNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_board_id")
    private Coffee_Board coffee_board;

    /**
     * Coffee_Board 랑 Reply가 일대다 관계이다.
     * 그걸 JPA방식으로 엔티티화 했는데 여기서 주의해야할점이
     * DB에서는 한쪽에 데이터를 추가하면 알아서 양쪽에 반영이 되지만
     * 여기선 객체여서 한쪽에 추가하면 다른한쪽에는 데이터가 추가가 안되서 불균형이 생긴다.
     * 그래서 한곳에 추가하면 다른한쪽도 데이터가 추가되도록
     * 연관관계 메소드를 작성해줘야 한다.
     */
    public void setCoffee_board(Coffee_Board coffee_board) {
        this.coffee_board = coffee_board;
        coffee_board.getReplys().add(this);
    }
}
