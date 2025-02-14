package com.example.mhsolution.repository.repositories;

import com.example.mhsolution.domain.data.Tables;
import com.example.mhsolution.domain.data.tables.pojos.Partners;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PartnerRepository {

    private final DSLContext dsl;

    public List<Partners> findAll() {
        return dsl.selectFrom(Tables.PARTNERS)
                .fetchInto(Partners.class);
    }
}

