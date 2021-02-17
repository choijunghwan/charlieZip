package study.charlieZip.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coffee_Board extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private Clob desc;

    @OneToMany(mappedBy = "coffee_board")
    private List<Reply> replys = new ArrayList<>();
}
