package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Seo;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeoRepository {
    private final DSLContext dsl;

    public Optional<Seo> findById(String seoId) {
        return Optional.ofNullable(
                dsl.selectFrom(Tables.SEO)
                        .where(Tables.SEO.SEO_ID.eq(seoId))
                        .fetchOneInto(Seo.class)
        );
    }
}
