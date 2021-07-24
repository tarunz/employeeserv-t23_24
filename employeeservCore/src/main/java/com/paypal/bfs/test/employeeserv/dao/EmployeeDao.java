package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao extends AbstractEntityDao<Employee> {
    public Employee get(int id) {
        return get(Employee.class, id);
    }
}
