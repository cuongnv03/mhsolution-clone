package com.example.mhsolution.repository.repositories;

import com.example.mhsolution.domain.data.Tables;
import com.example.mhsolution.domain.data.tables.pojos.Contact;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

    private final DSLContext dsl;

    public Contact findContact() {
        return dsl.selectFrom(Tables.CONTACT)
                .fetchOneInto(Contact.class);
    }
}

