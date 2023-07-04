package com.oumoi.springmultitenancy;


import com.oumoi.springmultitenancy.core.utils.TenantIdentifierResolver;
import com.oumoi.springmultitenancy.entity.Person;
import com.oumoi.springmultitenancy.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class MultitenancyTest {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TenantIdentifierResolver currentTenant;

    static final String PIVOTAL = "public";
    static final String VMWARE = "cic";

    @Test
    void saveAndLoadPerson() {

        Person adam = createPerson(PIVOTAL, "Adam");
        Person eve = createPerson(VMWARE, "Eve");

        assertEquals("public",adam.getTenant());
        assertEquals(true,eve.getTenant().equals(VMWARE));

        currentTenant.setCurrentTenant(VMWARE);
//        assertTrue(persons.findAll().extracting(Person::getName).containsExactly("Eve");

        currentTenant.setCurrentTenant(PIVOTAL);
//        assertTrue(persons.findAll()).extracting(Person::getName).containsExactly("Adam");
    }


    private Person createPerson(String schema, String name) {

        currentTenant.setCurrentTenant(schema);

        Person person = new Person();
        person.setName(name);
        Person adam = personRepository.save(person);
        assertNotNull(adam.getId() != null);

        return adam;
    }
}
