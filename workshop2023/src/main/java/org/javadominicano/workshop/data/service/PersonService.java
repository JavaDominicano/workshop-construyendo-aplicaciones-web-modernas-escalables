package org.javadominicano.workshop.data.service;

import org.javadominicano.workshop.data.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Optional<Person> get(Long id) {
        if (id == null) return Optional.empty();

        return repository.findById(id);
    }

    public Person update(Person entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Person> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Person> list(Pageable pageable, Specification<Person> filter) {
        return repository.findAll(filter, pageable);
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public int count() {
        return (int) repository.count();
    }

}
