package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.*;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IntroductionRepository {
    private final DSLContext dsl;

    public DataIntroduction findById(int id) {
        return dsl.selectFrom(Tables.DATA_INTRODUCTION)
                .where(Tables.DATA_INTRODUCTION.ID.eq(id))
                .fetchOneInto(DataIntroduction.class);
    }

    public List<Visions> findVisionsByIntroductionId(int introductionId) {
        return dsl.selectFrom(Tables.VISIONS)
                .where(Tables.VISIONS.DATA_INTRODUCTION_ID.eq(introductionId))
                .fetchInto(Visions.class);
    }

    public List<Missions> findMissionsByIntroductionId(int introductionId) {
        return dsl.selectFrom(Tables.MISSIONS)
                .where(Tables.MISSIONS.DATA_INTRODUCTION_ID.eq(introductionId))
                .fetchInto(Missions.class);
    }

    public List<CoreValues> findCoreValuesByIntroductionId(int introductionId) {
        return dsl.selectFrom(Tables.CORE_VALUES)
                .where(Tables.CORE_VALUES.DATA_INTRODUCTION_ID.eq(introductionId))
                .fetchInto(CoreValues.class);
    }

    public List<Competitives> findCompetitivesByIntroductionId(int introductionId) {
        return dsl.selectFrom(Tables.COMPETITIVES)
                .where(Tables.COMPETITIVES.DATA_INTRODUCTION_ID.eq(introductionId))
                .fetchInto(Competitives.class);
    }
}
