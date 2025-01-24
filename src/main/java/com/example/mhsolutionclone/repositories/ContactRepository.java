package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Contact;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

    private final DSLContext dsl;

    public Contact findContact() {
        return dsl.selectFrom(Tables.CONTACT)
                .fetchOneInto(Contact.class);
    }
}

