package study.charlieZip.domain.coffee.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import study.charlieZip.domain.coffee.dto.QcoffeeBoardDto;
import study.charlieZip.domain.coffee.dto.coffeeBoardDto;
import study.charlieZip.domain.coffee.dto.CoffeeSearchCondition;

import java.util.List;

import static org.springframework.util.StringUtils.*;
import static study.charlieZip.domain.coffee.entity.QCoffee_Board.coffee_Board;

@RequiredArgsConstructor
public class CoffeeRepositoryCustomImpl implements CoffeeRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<coffeeBoardDto> searchPage(CoffeeSearchCondition condition, Pageable pageable) {
        QueryResults<coffeeBoardDto> results = queryFactory
                .select(new QcoffeeBoardDto(
                        coffee_Board.id.as("coffee_board_id"),
                        coffee_Board.store_name,
                        coffee_Board.menu_name,
                        coffee_Board.price,
                        coffee_Board.sweet,
                        coffee_Board.acidity,
                        coffee_Board.body,
                        coffee_Board.balance,
                        coffee_Board.aftertaste,
                        coffee_Board.aroma))
                .from(coffee_Board)
                .where(
                        storeNameCt(condition.getStore_name()),
                        menuNameCt(condition.getMenu_name())
                )
                .orderBy(coffee_Board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<coffeeBoardDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression menuNameCt(String menu_name) {
        return hasText(menu_name) ? coffee_Board.menu_name.contains(menu_name) : null;
    }

    private BooleanExpression storeNameCt(String store_name) {
        return hasText(store_name) ? coffee_Board.store_name.contains(store_name) : null;
    }
}
