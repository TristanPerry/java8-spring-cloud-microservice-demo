package com.tristanperry.microservices.businesslogicapi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

public interface FizzBuzzRepository extends JpaRepository<FizzBuzz, Long> {

    Set<FizzBuzz> findByIsFizzBuzz(Boolean isFizzBuzz);

}
