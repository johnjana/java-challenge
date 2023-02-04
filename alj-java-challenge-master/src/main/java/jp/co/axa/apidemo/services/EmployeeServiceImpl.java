package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /** Set method to create employee repository object**/
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    /** Method to retrieve all employees
	 * return lists of employee
     *  
	 * **/
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
     /** Method to retrieve specific employee
    *  Input employeeId
    *  return an employee
     * **/
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }
    
    /** Method to save an employee
     *  Input employee object
     *  
      * **/
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    /** Method to delete an employee
     *  Input employee id
     *  
      * **/
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }
    /** Method to update an employee
     *  Input employee object
     *  
      * **/
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}