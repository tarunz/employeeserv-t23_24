package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import com.paypal.bfs.test.employeeserv.entity.Address;
import com.paypal.bfs.test.employeeserv.entity.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implementation class for employee resource.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeResourceImpl implements EmployeeResource {

    @NonNull
    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<EmployeeDto> employeeGetById(int id) {
        Employee employee = employeeService.employeeGetById(id);
        return new ResponseEntity<>(getEmployeeDtoFromEntity(employee), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeService.createNewEmployee(getEmployeeEntityFromDto(employeeDto));
        return new ResponseEntity<>(getEmployeeDtoFromEntity(employee), HttpStatus.OK);
    }

    private EmployeeDto getEmployeeDtoFromEntity(Employee employee) {
        com.paypal.bfs.test.employeeserv.api.dto.Address address = new com.paypal.bfs.test.employeeserv.api.dto.Address()
                .withCountry(employee.getAddress().getCountry())
                .withLine1(employee.getAddress().getLine1())
                .withLine2(employee.getAddress().getLine2())
                .withCountry(employee.getAddress().getCountry())
                .withState(employee.getAddress().getState())
                .withZipCode(employee.getAddress().getZipCode());
        return new EmployeeDto()
                .withFirstName(employee.getFirstName())
                .withLastName(employee.getLastName())
                .withAddress(address)
                .withDateOfBirth(employee.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .withId(employee.getId());
    }

    private Employee getEmployeeEntityFromDto(EmployeeDto employeeDto) {
        Address address = Address.builder()
                .country(employeeDto.getAddress().getCountry())
                .line1(employeeDto.getAddress().getLine1())
                .line2(employeeDto.getAddress().getLine2())
                .country(employeeDto.getAddress().getCountry())
                .state(employeeDto.getAddress().getState())
                .zipCode(employeeDto.getAddress().getZipCode()).build();
        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .address(address)
                .dateOfBirth(LocalDate.parse(employeeDto.getDateOfBirth()))
                .id(employeeDto.getId()).build();
    }
}
