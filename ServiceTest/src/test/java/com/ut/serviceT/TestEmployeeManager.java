package com.ut.serviceT;

import com.ut.sercieT.EmployeeDao;
import com.ut.sercieT.EmployeeManager;
import com.ut.sercieT.EmployeeVO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestEmployeeManager {

    @InjectMocks
    EmployeeManager manager;

    @Mock
    EmployeeDao employeeDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest()
    {
        List<EmployeeVO> list = new ArrayList<EmployeeVO>();
        EmployeeVO empOne = new EmployeeVO(1,"mike","jordan","test@qq.com");
        EmployeeVO empTwo = new EmployeeVO(2,"Lee","jordan","test2@qq.com");
        EmployeeVO empThree = new EmployeeVO(3,"justin","jordan","test3@qq.com");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeDao.getEmployeeList()).thenReturn(list);

        //test
        List<EmployeeVO> empList =manager.getEmployeeList();

        assertEquals(3,empList.size());
        verify(employeeDao,times(1)).getEmployeeList();
    }

}
