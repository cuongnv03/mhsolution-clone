package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Banners;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BannerRepository {
    private final DSLContext dsl;

    public List<Banners> findAll() {
        return dsl.selectFrom(Tables.BANNERS)
                .fetchInto(Banners.class);
    }
}
