package com.task.repository.specification;

import com.task.entity.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSpecification {

    public Specification<Employee> getAll() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
