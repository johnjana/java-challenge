package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	/** employeeService variable**/
    @Autowired
    private EmployeeService employeeService;

    /** setter method to create employee service object 
     *   Input employeeService
     * **/
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /** Method to retrieve  all employees
     * Output List of employees
     * **/
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }
    /** Method to retrieve employee by employee id
     * Input emloyeeId 
     * Output an employee
     * **/
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }
    /** Method to add an employee to the database
      * Input Employee object 
     *   
     * **/
    @PostMapping("/employees")
    public void saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }
    /** Method to delete specific employee by employee id
     * Input employeeId 
     *   
     * **/
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }
    /** Method to update the employee by employee id
    * Input employeeId,employee 
     *   
     * **/
    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }

    }

}
