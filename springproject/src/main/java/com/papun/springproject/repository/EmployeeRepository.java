package com.papun.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.papun.springproject.model.Employeee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employeee, Long>{

}
