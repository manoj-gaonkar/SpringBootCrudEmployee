package com.learn.crud.service.impl;
import com.learn.crud.exception.ResourceNotFoundException;
import com.learn.crud.model.Employee;
import com.learn.crud.repository.EmployeeRepository;
import com.learn.crud.service.EmployeeService;
import org.hibernate.metamodel.internal.EmbeddableInstantiatorPojoIndirecting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository emprepo;
    public Employee saveEmployee(Employee employee){
        return emprepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return emprepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id){
        Optional<Employee> emp = emprepo.findById(id);
        if(emp.isPresent()){
            return emp;
        }else{
            throw new ResourceNotFoundException("Employee","id",id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee,long id){
        Employee existingEmployee = emprepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id)
        );
        System.out.println(existingEmployee.getFirstname()+existingEmployee.getLastname()+existingEmployee.getEmail());

        if(employee.getFirstname() != null && !employee.getFirstname().isEmpty() && !employee.getFirstname().equals(existingEmployee.getFirstname()) ){
            existingEmployee.setFirstname(employee.getFirstname());
        }
        if(employee.getLastname() != null &&  !employee.getLastname().isEmpty() && !employee.getLastname().equals(existingEmployee.getLastname()) ){
            existingEmployee.setLastname(employee.getLastname());
        }if(employee.getEmail() != null && !employee.getEmail().isEmpty() && !employee.getEmail().equals(existingEmployee.getEmail()) ){
            existingEmployee.setEmail(employee.getEmail());
        }
        emprepo.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public Boolean deleteEmployee(long id){
        emprepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee","id",id)
        );

        emprepo.deleteById(id);

        return true;




    }


}
