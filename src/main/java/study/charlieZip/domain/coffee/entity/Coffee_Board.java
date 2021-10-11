package study.charlieZip.domain.coffee.entity;

import lombok.*;
import study.charlieZip.domain.member.entity.Member;
import study.charlieZip.domain.model.BaseEntity;
import study.charlieZip.domain.reply.entity.Reply;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coffee_Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_board_id")
    private Long id;

    private String menu_name;
    private int price;
    private String store_name;

    private int count;

    private int sweet;
    private int acidity;
    private int body;
    private int balance;
    private int aftertaste;
    private int aroma;

    @Column(name = "content")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "coffee_board", cascade = CascadeType.ALL)
    private List<Reply> replys = new ArrayList<>();

    @Builder
    public Coffee_Board(Long id, String menu_name, int price, String store_name, int count, int sweet, int acidity, int body, int balance, int aftertaste, int aroma, String desc, Member member, List<Reply> replys) {
        this.id = id;
        this.menu_name = menu_name;
        this.price = price;
        this.store_name = store_name;
        this.count = count;
        this.sweet = sweet;
        this.acidity = acidity;
        this.body = body;
        this.balance = balance;
        this.aftertaste = aftertaste;
        this.aroma = aroma;
        this.desc = desc;
        this.member = member;
        this.replys = replys;
    }

    public void changeStoreName(String store_name) {
        this.store_name = store_name;
    }
}
