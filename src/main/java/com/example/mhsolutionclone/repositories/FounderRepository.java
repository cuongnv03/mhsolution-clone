package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Founders;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FounderRepository {

    private final DSLContext dsl;

    public List<Founders> findAll() {
        return dsl.selectFrom(Tables.FOUNDERS)
                .fetchInto(Founders.class);
    }
}
