package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.request.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JobRepository {

    private final DSLContext dsl;

    public List<Jobs> findAll() {
        return dsl.selectFrom(Tables.JOBS)
                .orderBy(Tables.JOBS.END_TIME.desc())
                .fetchInto(Jobs.class);
    }

    public Jobs findBySeoId(String seoId) {
        return dsl.selectFrom(Tables.JOBS)
                .where(Tables.JOBS.SEO_ID.eq(seoId))
                .fetchOneInto(Jobs.class);
    }

    public Map<List<Jobs>, Long> searchAll(int page, int size) {
        var jobs = dsl.select(Tables.JOBS.asterisk(),
                DSL.count().over().as("total_elements"))
                .from(Tables.JOBS)
                .orderBy(Tables.JOBS.END_TIME.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
        return Map.of(jobs.into(Jobs.class), jobs.getFirst().get("total_elements", Long.class));
    }

    public Map<List<Jobs>, Long> search(List<SearchFilter> filters, int page, int size) {
        Condition condition = buildCondition(filters);
        var jobs = dsl.select(Tables.JOBS.asterisk(),
                        DSL.count().over().as("total_elements"))
                .from(Tables.JOBS)
                .where(condition)
                .orderBy(Tables.JOBS.END_TIME.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
        return Map.of(jobs.into(Jobs.class), jobs.getFirst().get("total_elements", Long.class));
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
        Field<Object> field = Tables.JOBS.field(filter.getName(), Object.class);
        if (field == null) {
            throw new IllegalArgumentException("Invalid field name: " + filter.getName());
        }
        Object value = filter.getValue();

        return switch (filter.getOperation()) {
            case "eq" -> field.eq(value);
            default -> throw new IllegalArgumentException("Unsupported operator: " + filter.getOperation());
        };
    }
}
