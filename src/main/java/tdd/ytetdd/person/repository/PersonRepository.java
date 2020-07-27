package tdd.ytetdd.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.ytetdd.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String s);
}
