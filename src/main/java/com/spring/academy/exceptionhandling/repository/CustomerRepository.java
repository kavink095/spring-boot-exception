package com.spring.academy.exceptionhandling.repository;

import com.spring.academy.exceptionhandling.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsById(Integer integer);

    Customer findCustomerByCusId(int id);

    boolean existsByName(String name);

}
