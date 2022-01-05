package com.hrm.test.business.service.impl;

import com.hrm.test.business.dao.EmployeeDao;
import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.business.service.EmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public PagingDTO<Employee> listEmployees(String query, Pageable pageable) {
        return employeeDao.getEmployees(query, pageable);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public Employee get(long id) {
        return employeeDao.get(id);
    }

    @Override
    public void delete(long id) {
        employeeDao.delete(id);
    }
}
