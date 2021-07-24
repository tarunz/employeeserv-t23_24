package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.entity.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;

public interface EmployeeService {
    Employee employeeGetById(int id) throws EmployeeNotFoundException;
    Employee createNewEmployee(Employee employee) throws InvalidRequestException;
}