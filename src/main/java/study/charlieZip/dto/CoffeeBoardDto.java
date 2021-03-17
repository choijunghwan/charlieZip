package study.charlieZip.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import study.charlieZip.entity.Coffee_Board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CoffeeBoardDto {

    private Long id;

    @NotBlank(message = "가게이름을 입력해주세요")
    private String store_name;

    private String menu_name;
    private int price;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int sweet;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int acidity;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int body;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int balance;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int aftertaste;

    @Range(min = 1, max = 60, message = "1~60 사이 숫자를입력해주세요.")
    private int aroma;

    @NotBlank(message = "최소 1글자는 입력해주세요. 부탁드립니다!")
    private String desc;


    @Builder
    public CoffeeBoardDto(Long id, String store_name, String menu_name, int price, int sweet, int acidity, int body, int balance, int aftertaste, int aroma, String desc) {
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
    }

}
