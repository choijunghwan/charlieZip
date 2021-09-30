package study.charlieZip.domain.coffee.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CoffeeBoardUpdateForm {

    @NotNull
    private Long id;

    @NotBlank(message = "가게이름을 입력해주세요")
    private String store_name;

    @NotBlank
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
    public CoffeeBoardUpdateForm(Long id, String store_name, String menu_name, int price, int sweet, int acidity, int body, int balance, int aftertaste, int aroma, String desc) {
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
