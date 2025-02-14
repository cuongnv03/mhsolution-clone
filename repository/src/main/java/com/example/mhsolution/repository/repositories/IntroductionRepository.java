package com.example.mhsolution.repository.repositories;

import com.example.mhsolution.domain.data.Tables;
import com.example.mhsolution.domain.data.tables.pojos.*;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IntroductionRepository {
    private final DSLContext dsl;

    public Introduction findById(int id) {
        return dsl.selectFrom(Tables.INTRODUCTION)
                .where(Tables.INTRODUCTION.ID.eq(id))
                .fetchOneInto(Introduction.class);
    }
}
