package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    //load employee data
    private List<Employee> employees;
    @PostConstruct
    private void loadData () {
        // create employee
        Employee emp1 = new Employee(1,"Charles","Xavier","charles@luv2code.com");
        Employee emp2 = new Employee(2,"Erik","Lehnsherr","erik@luv2code.com");
        Employee emp3 = new Employee(3,"Logan   ","Wolverine","logan@luv2code.com");

        // create the list
        employees = new ArrayList<>();
        // add to the list
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        theModel.addAttribute("employees",employees);
        return "list-employees";
    }
}
