package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.JobInfos;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Jobs;
import com.example.mhsolutionclone.data.request.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Jobs> searchAll(int page, int size) {
        return dsl.selectFrom(Tables.JOBS)
                .orderBy(Tables.JOBS.END_TIME.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetchInto(Jobs.class);
    }

    public long count() {
        return dsl.fetchCount(Tables.JOBS);
    }

    public List<JobInfos> findInfosByJobId(int jobId) {
        return dsl.selectFrom(Tables.JOB_INFOS)
                .where(Tables.JOB_INFOS.JOB_ID.eq(jobId))
                .fetchInto(JobInfos.class);
    }

    public List<Jobs> search(List<SearchFilter> filters, int page, int size) {
        Condition condition = buildCondition(filters);
        return dsl.selectFrom(Tables.JOBS)
                .where(condition)
                .orderBy(Tables.JOBS.END_TIME.desc())
                .limit(size)
                .offset((page - 1) * size)
                .fetchInto(Jobs.class);
    }

    public long countSearch(List<SearchFilter> filters) {
        Condition condition = buildCondition(filters); // Build jOOQ condition from filters
        return dsl.fetchCount(dsl.selectFrom(Tables.JOBS).where(condition));
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
