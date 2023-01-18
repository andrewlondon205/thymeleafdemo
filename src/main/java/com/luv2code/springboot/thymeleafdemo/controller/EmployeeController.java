package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> employees = service.findAll();
        theModel.addAttribute("employees",employees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model theModel) {
        //create a model attribute to bind form data
        Employee theEmployee = new Employee () ;
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate (@RequestParam("employeeId") int id, Model theModel) {
        // get the employee as a model attribute to pre-populate the form
        Employee theEmployee = service.findById(id);
        theModel.addAttribute("employee",theEmployee);
        // send over to our form
        return "employees/employee-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        // save the employee
        service.save(theEmployee);
        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete (@RequestParam("employeeId") int id) {
        service.delete(id);
        return "redirect:/employees/list";
    }




}
