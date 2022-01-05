package com.hrm.test.business.controller;

import com.hrm.test.business.dto.PagingDTO;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.business.entity.Employee;
import com.hrm.test.business.service.EmployeeService;
import com.hrm.test.shared.validation.ValidAge;
import com.hrm.test.shared.validation.ValidQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<PagingDTO<Employee>> listEmployees(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                             @ValidQuery @RequestParam(required = false) String query) {
        return new ResponseEntity<>(employeeService.listEmployees(query, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody @ValidAge Employee employee){
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody @ValidAge Employee employee){
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id){
        return new ResponseEntity<>(employeeService.get(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public void deleteEmployee(@PathVariable long id){
        employeeService.delete(id);
    }
}
