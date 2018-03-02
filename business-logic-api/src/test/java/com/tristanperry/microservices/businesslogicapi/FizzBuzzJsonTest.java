package com.tristanperry.microservices.businesslogicapi;

import com.tristanperry.microservices.businesslogicapi.json.FizzBuzzJson;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzz;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzzRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class FizzBuzzJsonTest {

    @Test
    public void testDefaultConstructor() {
        assertNotNull(new FizzBuzzJson());
    }

    @Test
    public void testBasicConstructor() {
        final String fizzValue = "f34rwef";
        final FizzBuzzJson fizzBuzzJson = new FizzBuzzJson(fizzValue);
        assertNotNull(fizzBuzzJson);
        assertEquals(fizzValue, fizzBuzzJson.getFizz());
        assertNull(fizzBuzzJson.getBuzz());
        assertNull(fizzBuzzJson.getFizzBuzz());
        assertNull(fizzBuzzJson.getId());
    }

    @Test
    public void testMainConstructor() {
        final String fizzValue = "f34rwef";
        final String buzzValue = "buz1";
        final Boolean isFizzBuzz = Boolean.FALSE;

        final FizzBuzzJson fizzBuzzJson = new FizzBuzzJson(new FizzBuzz(fizzValue, buzzValue, isFizzBuzz));
        assertNotNull(fizzBuzzJson);
        assertEquals(fizzValue, fizzBuzzJson.getFizz());
        assertEquals(buzzValue, fizzBuzzJson.getBuzz());
        assertEquals(isFizzBuzz, fizzBuzzJson.getFizzBuzz());
        assertNull(fizzBuzzJson.getId());
    }

}
