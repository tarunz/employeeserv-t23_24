package com.paypal.bfs.test.employeeserv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.paypal.bfs.test.employeeserv.api.dto.Address;
import com.paypal.bfs.test.employeeserv.api.dto.EmployeeDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EmployeeservApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringIntegrationTest
@FixMethodOrder(MethodSorters.JVM)
public class EmployeeServiceIntegrationTest {

    private final ObjectMapper objectMapper;
    private final AsyncHttpClient httpClient;

    private static final String BASE_URL = "http://localhost:8080/v1/bfs/employees";

    public EmployeeServiceIntegrationTest() throws IOException {
        httpClient = new AsyncHttpClient();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void aTestMandatoryLastName() throws InterruptedException, ExecutionException, JsonProcessingException {
        EmployeeDto employeeDto = new EmployeeDto().withFirstName("Tarun");
        Response actualResponse = createEmployeeRequest(employeeDto);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actualResponse.getStatusCode());
    }

    @Test
    public void aTestMandatoryZipCode() throws InterruptedException, ExecutionException, JsonProcessingException {
        Address address = new Address().withLine1("PB").withState("BK").withCountry("IN");
        EmployeeDto employeeDto = new EmployeeDto().withFirstName("Tarun").withLastName("K").withDateOfBirth("2000-01-01").withAddress(address);
        Response actualResponse = createEmployeeRequest(employeeDto);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actualResponse.getStatusCode());
    }

    @Test
    public void aTestValidZipCode() throws InterruptedException, ExecutionException, JsonProcessingException {
        Address address = new Address().withLine1("PB").withState("BK").withCountry("IN").withZipCode("2232");
        EmployeeDto employeeDto = new EmployeeDto().withFirstName("Tarun").withLastName("K").withDateOfBirth("2000-01-01").withAddress(address);
        Response actualResponse = createEmployeeRequest(employeeDto);
        assertEquals(HttpStatus.BAD_REQUEST.value(), actualResponse.getStatusCode());
    }

    @Test
    public void aTestInvalidGetId() throws InterruptedException, ExecutionException, JsonProcessingException {
        Response actualResponse = getEmployeeRequest("jhds-cdcv-dvfdv");
        assertEquals(HttpStatus.BAD_REQUEST.value(), actualResponse.getStatusCode());
    }

    @Test
    public void aTestEmployeeNotFound() throws InterruptedException, ExecutionException {
        Response actualResponse = getEmployeeRequest("2");
        assertEquals(HttpStatus.NOT_FOUND.value(), actualResponse.getStatusCode());
    }

    @Test
    public void bTestValidZipCode() throws InterruptedException, ExecutionException, JsonProcessingException {
        Response actualResponse = createEmployeeRequest(getValidEmployee());
        assertEquals(HttpStatus.OK.value(), actualResponse.getStatusCode());
    }

    @Test
    public void cTestValidZipCode() throws InterruptedException, ExecutionException, IOException {
        Response actualResponse = getEmployeeRequest("1");
        EmployeeDto expectedEmployee = getValidEmployee();
        expectedEmployee.setId(1);
        assertEquals(objectMapper.writeValueAsString(expectedEmployee), actualResponse.getResponseBody());
    }

    private EmployeeDto getValidEmployee() {
        Address address = new Address().withLine1("PB").withState("BK").withCountry("IN").withZipCode("223233");
        return new EmployeeDto().withFirstName("Tarun").withLastName("K").withDateOfBirth("2000-01-01").withAddress(address);
    }

    private Response createEmployeeRequest(EmployeeDto body) throws JsonProcessingException, ExecutionException, InterruptedException {
        return httpClient.preparePost(BASE_URL)
                .setBody(objectMapper.writeValueAsString(body))
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .execute().get();
    }

    private Response getEmployeeRequest(String id) throws ExecutionException, InterruptedException {
        return httpClient.prepareGet(BASE_URL + "/" + id)
                .execute().get();
    }

}
