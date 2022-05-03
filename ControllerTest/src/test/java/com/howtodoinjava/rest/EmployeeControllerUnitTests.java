package com.howtodoinjava.rest;

import com.howtodoinjava.rest.controller.EmployeeController;
import com.howtodoinjava.rest.dao.EmployeeRepository;
import com.howtodoinjava.rest.model.Employee;
import com.howtodoinjava.rest.model.Employees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerUnitTests {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void testAddEmployee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee();
        employee.setId(1);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee employeeToAdd = new Employee("Lokesh", "Gupta", "howtodoinjava@gmail.com");
        ResponseEntity<Object> responseEntity = employeeController.addEmployee(employeeToAdd);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void testFindAll(){
        //given
        Employee employee1 = new Employee("xiao","li","123@qq.com");
        Employee employee2 = new Employee("xiao","wang","124@qq.com");
        Employee employee3 = new Employee("xiao","zhang","1235@qq.com");

        List<Employee> list = new ArrayList<Employee>();
        list.addAll(Arrays.asList(employee1,employee2,employee3));

        when(employeeRepository.findAll()).thenReturn(list);
        //when
        Employees result = employeeController.getEmployees();

        //then
        assertThat(result.getEmployeeList().size()).isEqualTo(3);

        assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(employee1.getFirstName());

        assertThat(result.getEmployeeList().get(1).getFirstName()).isEqualTo(employee2.getFirstName());
// test github issue

    }
}
