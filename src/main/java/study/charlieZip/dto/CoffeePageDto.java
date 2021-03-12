package study.charlieZip.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class CoffeePageDto {

    private Long id;
    private String store_name;
    private String menu_name;
    private int price;

    @QueryProjection
    public CoffeePageDto(Long id, String store_name, String menu_name, int price) {
        this.id = id;
        this.store_name = store_name;
        this.menu_name = menu_name;
        this.price = price;
    }
}
