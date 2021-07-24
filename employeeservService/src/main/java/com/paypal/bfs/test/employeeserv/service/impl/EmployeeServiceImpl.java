package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.utils.ErrorCode;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.entity.Employee;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.exception.InvalidRequestException;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    @NonNull
    private final EmployeeDao employeeDao;

    @Override
    public Employee employeeGetById(int id) throws EmployeeNotFoundException {
        return Optional.ofNullable(employeeDao.get(id)).orElseThrow(() -> new EmployeeNotFoundException(ErrorCode.EMP_NOT_FOUND.getMessage()));
    }

    @Override
    public Employee createNewEmployee(Employee employee) throws InvalidRequestException {
        try {
            employeeDao.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidRequestException(ErrorCode.DUPLICATE_EMPLOYEE_WITH_ID.getMessage());
        }
        return employee;
    }
}
