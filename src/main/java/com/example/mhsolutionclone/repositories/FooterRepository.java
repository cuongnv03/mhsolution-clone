package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.DataFooter;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.FooterSocials;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FooterRepository {

    private final DSLContext dsl;

    public DataFooter findFooter() {
        return dsl.selectFrom(Tables.DATA_FOOTER)
                .fetchOneInto(DataFooter.class);
    }

    public List<FooterSocials> findSocialsByFooterId(int footerId) {
        return dsl.selectFrom(Tables.FOOTER_SOCIALS)
                .where(Tables.FOOTER_SOCIALS.FOOTER_ID.eq(footerId))
                .fetchInto(FooterSocials.class);
    }
}

