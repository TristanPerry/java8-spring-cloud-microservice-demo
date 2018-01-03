package com.tristanperry.microservices.businesslogicapi;

import com.tristanperry.microservices.businesslogicapi.model.FizzBuzz;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzzRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FizzBuzzRepositoryTest {

    @Autowired
    private FizzBuzzRepository fizzBuzzRepository;

    @Test
    public void contextLoads() {
        assertNotNull(fizzBuzzRepository);
    }

    @Test
    public void testBasicSave() {
        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing", "Buzzing", Boolean.TRUE)));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSave_fail_null() {
        fizzBuzzRepository.save(new FizzBuzz());
    }

    @Test
    public void testFindByMethod() {
        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing0", "Buzzing0", Boolean.TRUE)));
        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing1", "Buzzing1", Boolean.TRUE)));
        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing2", "Buzzing2", Boolean.FALSE)));
        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing3", "Buzzing3", Boolean.TRUE)));

        assertEquals(fizzBuzzRepository.count(), 4L);
        assertEquals(fizzBuzzRepository.findByIsFizzBuzz(Boolean.FALSE).size(), 1L);
        assertEquals(fizzBuzzRepository.findByIsFizzBuzz(Boolean.TRUE).size(), 3L);

        assertNotNull(fizzBuzzRepository.save(new FizzBuzz("Fizzing4", "Buzzing4", Boolean.TRUE)));
        assertEquals(fizzBuzzRepository.findByIsFizzBuzz(Boolean.FALSE).size(), 1L);
        assertEquals(fizzBuzzRepository.findByIsFizzBuzz(Boolean.TRUE).size(), 4L);
    }

}
