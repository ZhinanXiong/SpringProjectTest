package com.howtodoinjava.rest;

import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = SpringBootDemoApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({"schema.sql", "data.sql" })

    @Test
    public void testAddEmployee(){
        assertTrue(this.restTemplate
        .getForObject("http://localhost:"+port+"/employees", Employees.class).getEmployeeList().size() == 3);
    }
}
