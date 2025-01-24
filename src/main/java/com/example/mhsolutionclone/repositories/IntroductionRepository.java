package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.*;
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
