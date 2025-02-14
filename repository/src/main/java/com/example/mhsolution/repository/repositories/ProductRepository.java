package com.example.mhsolution.repository.repositories;

import com.example.mhsolution.domain.data.Tables;
import com.example.mhsolution.domain.data.tables.pojos.Products;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DSLContext dsl;

    public List<Products> findAll() {
        return dsl.selectFrom(Tables.PRODUCTS)
                .fetchInto(Products.class);
    }

    public Products findById(Integer id) {
        return dsl.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.ID.eq(id))
                .fetchOneInto(Products.class);
    }
}

