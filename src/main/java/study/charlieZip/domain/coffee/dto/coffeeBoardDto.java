package study.charlieZip.domain.coffee.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class coffeeBoardDto {

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

    @QueryProjection
    public coffeeBoardDto(Long id, String store_name, String menu_name, int price, int sweet, int acidity, int body, int balance, int aftertaste, int aroma) {
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
    }
}
