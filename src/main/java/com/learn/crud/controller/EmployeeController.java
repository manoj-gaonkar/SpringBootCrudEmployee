package com.learn.crud.controller;

import com.learn.crud.model.Employee;
import com.learn.crud.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("newemployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        System.out.println(employee.getFirstname());

        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id") long id){
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if(emp.isPresent()){
            return ResponseEntity.ok().body(emp);
        }else{
            return ResponseEntity.notFound().build();
        }
//        return new ResponseEntity<Optional<Employee>>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee ,@PathVariable("id") long employeeId){
        System.out.println(employee.getFirstname()+employee.getEmail()+employee.getLastname());
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,employeeId),HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") long id){
        return new ResponseEntity<Boolean>(employeeService.deleteEmployee(id),HttpStatus.OK);
    }

}
