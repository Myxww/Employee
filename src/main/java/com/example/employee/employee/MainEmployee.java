package com.example.employee.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.employee.model.Employee;

@Repository
public interface MainEmployee extends JpaRepository<Employee, Long> {

}
