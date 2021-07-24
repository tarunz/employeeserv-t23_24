package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link EmployeeDto} resource by id.
     *
     * @param id employee id.
     * @return {@link EmployeeDto} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<EmployeeDto> employeeGetById(@PathVariable("id") int id);

    /**
     * Creates new {@link EmployeeDto} resource.
     *
     * @param employeeDto employee.
     * @return {@link EmployeeDto} resource.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/v1/bfs/employees")
    ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto);
}
