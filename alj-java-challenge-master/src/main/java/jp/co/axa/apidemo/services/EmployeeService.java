package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

	/** Method to retrieve all employees
	 * return lists of employee
     *  
	 * **/
    public List<Employee> retrieveEmployees();

    /** Method to retrieve specific employee
    *  Input employeeId
    *  return an employee
     * **/
    public Employee getEmployee(Long employeeId);

    /** Method to save an employee
    *  Input employee object
    *  
     * **/
    public void saveEmployee(Employee employee);

    /** Method to delete an employee
    *  Input employee id
    *  
     * **/
    public void deleteEmployee(Long employeeId);

    /** Method to update an employee
    *  Input employee object
    *  
     * **/
    public void updateEmployee(Employee employee);
}