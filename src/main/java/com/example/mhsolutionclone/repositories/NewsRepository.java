package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Categories;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.News;
import com.example.mhsolutionclone.data.request.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepository {

    private final DSLContext dsl;

    public List<News> findAll() {
        return dsl.selectFrom(Tables.NEWS)
                .orderBy(Tables.NEWS.CREATED_AT.desc())
                .fetchInto(News.class);
    }

    public List<News> searchAll(int page, int size) {
        return dsl.selectFrom(Tables.NEWS)
                .orderBy(Tables.NEWS.CREATED_AT.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetchInto(News.class);
    }

    public List<News> searchByCategoryId(List<SearchFilter> filters, int page, int size) {
        Condition condition = buildCondition(filters);
        return dsl.selectFrom(Tables.NEWS)
                .where(condition)
                .orderBy(Tables.NEWS.CREATED_AT.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetchInto(News.class);
    }

    public News findBySeoId(String seoId) {
        return dsl.selectFrom(Tables.NEWS)
                .where(Tables.NEWS.SEO_ID.eq(seoId))
                .fetchOneInto(News.class);
    }

    public long count() {
        return dsl.fetchCount(Tables.NEWS);
    }

    public long countSearch(List<SearchFilter> filters) {
        Condition condition = buildCondition(filters); // Build jOOQ condition from filters
        return dsl.fetchCount(dsl.selectFrom(Tables.NEWS).where(condition));
    }

    // Xây dựng điều kiện tìm kiếm từ bộ lọc
    private Condition buildCondition(List<SearchFilter> filters) {
        if (filters == null || filters.isEmpty()) {
            return DSL.noCondition();
        }

        Condition condition = createCondition(filters.removeFirst());
        for (SearchFilter filter : filters) {
            condition = condition.and(createCondition(filter));
        }
        return condition;
    }

    // Tạo điều kiện tìm kiếm từ một bộ lọc
    private Condition createCondition(SearchFilter filter) {
        Field<Object> field = Tables.NEWS.field(filter.getName(), Object.class);
        if (field == null) {
            throw new IllegalArgumentException("Invalid field name: " + filter.getName());
        }
        Object value = castToRequiredType(filter.getName(), filter.getValue());

        return switch (filter.getOperation()) {
            case "eq" -> field.eq(value);
            default -> throw new IllegalArgumentException("Unsupported operator: " + filter.getOperation());
        };
    }

    // Chuyển kiểu dữ liệu theo yêu cầu
    private Object castToRequiredType(String name, String value) {
        if (value == null) return null;
        return switch (name) {
            case "category_new_id" -> Integer.parseInt(value);
            default -> value;
        };
    }

    public Optional<Categories> findCategoryById(int id) {
        return Optional.ofNullable(
                dsl.selectFrom(Tables.CATEGORIES)
                        .where(Tables.CATEGORIES.ID.eq(id))
                        .fetchOneInto(Categories.class)
        );
    }
}

