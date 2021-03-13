package study.charlieZip.dto;

import lombok.Data;

@Data
public class CoffeeSearchCondition {

    private String store_name;
    private String menu_name;

    public CoffeeSearchCondition(String store_name, String menu_name) {
        this.store_name = store_name;
        this.menu_name = menu_name;
    }
}
