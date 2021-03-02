package study.charlieZip.dto;

import lombok.*;
import study.charlieZip.entity.Coffee_Board;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CoffeeBoardDto {

    private Long id;
    private String store_name;
    private String menu_name;
    private int price;

    private int sweet;
    private int acidity;
    private int body;
    private int balance;
    private int aftertaste;
    private int aroma;
    private String desc;

    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;


    @Builder
    public CoffeeBoardDto(Long id, String store_name, String menu_name, int price, int sweet, int acidity, int body, int balance, int aftertaste, int aroma, String desc, LocalDateTime createDate, LocalDateTime lastModifiedDate, String lastModifiedBy) {
        this.id = id;
        this.store_name = store_name;
        this.menu_name = menu_name;
        this.price = price;
        this.sweet = sweet;
        this.acidity = acidity;
        this.body = body;
        this.balance = balance;
        this.aftertaste = aftertaste;
        this.aroma = aroma;
        this.desc = desc;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}