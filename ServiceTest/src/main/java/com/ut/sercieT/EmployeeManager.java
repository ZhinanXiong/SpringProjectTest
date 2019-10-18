package com.ut.sercieT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ut.sercieT.EmployeeDao;

@Service
public class EmployeeManager
{
    @Autowired
    EmployeeDao dao;

    public List<EmployeeVO> getEmployeeList() {
        return dao.getEmployeeList();
    }

    public EmployeeVO getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    public void addEmployee(EmployeeVO employee) {
        dao.addEmployee(employee);
    }
}
