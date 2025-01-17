package com.example.mhsolutionclone.repositories;

import com.example.mhsolution.mhsolutionclone.jooq.Tables;
import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.ContactForms;
import com.example.mhsolution.mhsolutionclone.jooq.tables.records.ContactFormsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactFormRepository {

    private final DSLContext dsl;

    public ContactForms save(ContactForms contactForm) {
        ContactFormsRecord record = dsl.newRecord(Tables.CONTACT_FORMS, contactForm);
        record.store();
        return record.into(ContactForms.class);
    }

    public List<ContactForms> findAll() {
        return dsl.selectFrom(Tables.CONTACT_FORMS)
                .orderBy(Tables.CONTACT_FORMS.SUBMITTED_AT.desc())
                .fetchInto(ContactForms.class);
    }

    public Optional<ContactForms> findById(int id) {
        return Optional.ofNullable(
                dsl.selectFrom(Tables.CONTACT_FORMS)
                        .where(Tables.CONTACT_FORMS.ID.eq(id))
                        .fetchOneInto(ContactForms.class)
        );
    }
}
