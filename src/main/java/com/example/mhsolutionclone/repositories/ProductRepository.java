package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ProductContents;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ProductInfos;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Products;
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

    public Products findBySeoId(String seoId) {
        return dsl.selectFrom(Tables.PRODUCTS)
                .where(Tables.PRODUCTS.SEO_ID.eq(seoId))
                .fetchOneInto(Products.class);
    }

    public List<ProductContents> findContentsByProductId(int productId) {
        return dsl.selectFrom(Tables.PRODUCT_CONTENTS)
                .where(Tables.PRODUCT_CONTENTS.PRODUCT_ID.eq(productId))
                .fetchInto(ProductContents.class);
    }

    public List<ProductInfos> findInfosByProductId(int productId) {
        return dsl.selectFrom(Tables.PRODUCT_INFOS)
                .where(Tables.PRODUCT_INFOS.PRODUCT_ID.eq(productId))
                .fetchInto(ProductInfos.class);
    }
}

