package com.ut.serviceT;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ut.sercieT.EmployeeDao;
import com.ut.sercieT.EmployeeVO;
import com.ut.sercieT.EmployeeManager;

public class TestEmployeeManager {

    @InjectMocks
    EmployeeManager manager;

    @Mock
    EmployeeDao dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<EmployeeVO> list = new ArrayList<EmployeeVO>();
        EmployeeVO empOne = new EmployeeVO(1, "John", "John", "howtodoinjava@gmail.com");
        EmployeeVO empTwo = new EmployeeVO(2, "Alex", "kolenchiski", "alexk@yahoo.com");
        EmployeeVO empThree = new EmployeeVO(3, "Steve", "Waugh", "swaugh@gmail.com");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(dao.getEmployeeList()).thenReturn(list);

        //test
        List<EmployeeVO> empList = manager.getEmployeeList();

        assertEquals(3, empList.size());
        verify(dao, times(1)).getEmployeeList();
    }

    @Test
    public void getEmployeeByIdTest()
    {
        when(dao.getEmployeeById(1)).thenReturn(new EmployeeVO(1,"Lokesh","Gupta","user@email.com"));

        EmployeeVO emp = manager.getEmployeeById(1);

        assertEquals("Lokesh", emp.getFirstName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @Test
    public void createEmployeeTest()
    {
        EmployeeVO emp = new EmployeeVO(1,"Lokesh","Gupta","user@email.com");

        manager.addEmployee(emp);

        verify(dao, times(1)).addEmployee(emp);
    }
}
