package com.oumoi.springmultitenancy.repository;


import com.oumoi.springmultitenancy.entity.Person;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
