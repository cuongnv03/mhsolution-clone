package com.example.mhsolution.repository.repositories;

import com.example.mhsolution.domain.data.Tables;
import com.example.mhsolution.domain.data.models.search.SearchRequest;
import com.example.mhsolution.domain.data.models.search.SearchSort;
import com.example.mhsolution.domain.data.tables.pojos.Categories;
import com.example.mhsolution.domain.data.tables.pojos.News;
import com.example.mhsolution.domain.data.models.search.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class NewsRepository {

    private final DSLContext dsl;

    public List<News> findAll() {
        return dsl.selectFrom(Tables.NEWS)
                .orderBy(Tables.NEWS.CREATED_AT.desc())
                .fetchInto(News.class);
    }

    public News findById(Integer id) {
        return dsl.selectFrom(Tables.NEWS)
                .where(Tables.NEWS.ID.eq(id))
                .fetchOneInto(News.class);
    }

    public Map<List<News>, Long> search(SearchRequest searchRequest) {
        List<SearchFilter> filters = searchRequest.getFilters();
        int page = searchRequest.getPage();
        int size = searchRequest.getLimit();
        String sortProperty = searchRequest.getSorts().getProperty();
        SearchSort.SortDirection sortDirection = searchRequest.getSorts().getDirection();

        // Use filters if available, otherwise build an empty condition
        Condition condition = (filters == null || filters.isEmpty()) ? DSL.trueCondition() : buildCondition(filters);
        var news = dsl.select(Tables.NEWS.asterisk(),
                        DSL.count().over().as("total_elements"))
                .from(Tables.NEWS)
                .where(condition)
                .orderBy(
                        sortDirection == SearchSort.SortDirection.ASC ?
                                Tables.NEWS.field(sortProperty).asc() :
                                Tables.NEWS.field(sortProperty).desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
        return Map.of(news.into(News.class), news.getFirst().get("total_elements", Long.class));
    }

    // Xây dựng điều kiện tìm kiếm từ bộ lọc
    private Condition buildCondition(List<SearchFilter> filters) {
        if (filters == null || filters.isEmpty()) {
            return DSL.noCondition();
        }

        Condition condition = createCondition(filters.removeFirst());
        for (SearchFilter filter : filters) {
            condition.and(createCondition(filter));
        }
        return condition;
    }

    // Tạo điều kiện tìm kiếm từ một bộ lọc
    private Condition createCondition(SearchFilter filter) {
        Field<Object> field = Tables.NEWS.field(filter.getName(), Object.class);
        if (field == null) {
            throw new IllegalArgumentException("Invalid field name: " + filter.getName());
        }
        Object value = filter.getValue();

        return switch (filter.getOperation()) {
            case "eq" -> field.eq(value);
            case "like_ignore_case" -> field.likeIgnoreCase(value.toString());  // Case-insensitive like operator
            default -> throw new IllegalArgumentException("Unsupported operator: " + filter.getOperation());
        };
    }

//    private Object castToRequiredType(String name, String value) {
//        if (value == null) return null;
//        return switch (name) {
//            case "category_new_id" -> Integer.parseInt(value);
//            default -> value;
//        };
//    }

    public Optional<Categories> findCategoryById(int id) {
        return Optional.ofNullable(
                dsl.selectFrom(Tables.CATEGORIES)
                        .where(Tables.CATEGORIES.ID.eq(id))
                        .fetchOneInto(Categories.class)
        );
    }

    public List<Categories> findCategoriesByIds(Set<Integer> ids) {
        return dsl.selectFrom(Tables.CATEGORIES)
                .where(Tables.CATEGORIES.ID.in(ids))
                .fetchInto(Categories.class);
    }
}
