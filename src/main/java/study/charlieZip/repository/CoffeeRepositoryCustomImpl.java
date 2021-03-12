package study.charlieZip.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import study.charlieZip.dto.CoffeePageDto;
import study.charlieZip.dto.QCoffeePageDto;
import study.charlieZip.entity.Member;
import study.charlieZip.entity.QCoffee_Board;
import study.charlieZip.entity.QMember;

import java.util.List;

import static study.charlieZip.entity.QCoffee_Board.*;
import static study.charlieZip.entity.QMember.*;

@RequiredArgsConstructor
public class CoffeeRepositoryCustomImpl implements CoffeeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CoffeePageDto> searchPage(Pageable pageable) {
        QueryResults<CoffeePageDto> results = queryFactory
                .select(new QCoffeePageDto(
                        coffee_Board.id.as("coffee_board_id"),
                        coffee_Board.store_name,
                        coffee_Board.menu_name,
                        coffee_Board.price))
                .from(coffee_Board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<CoffeePageDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
