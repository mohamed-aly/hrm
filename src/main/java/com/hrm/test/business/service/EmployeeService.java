package com.hrm.test.business.service;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.business.entity.Employee;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    PagingDTO<Employee> listEmployees(String query, Pageable pageable);

    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee get(long id);

    void delete(long id);
}
