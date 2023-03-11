package jp.co.axa.apidemo.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
	
	/** employeeServcie**/
	@InjectMocks
	EmployeeServiceImpl employeeServcie;
	
	/** mocking employeeRepository**/
	@Mock
	EmployeeRepository employeeRepository;
	
	/** method to test the find all employees method**/
	@Test
	public void testGetEmployeeList() {
		
		 List<Employee> employees = new ArrayList<Employee>();
		 Employee e = new Employee(1L,"sai",20000,"IT");
		 
		 employees.add(e);
		 when(employeeRepository.findAll()).thenReturn(employees);	
		 assertTrue(employeeServcie.retrieveEmployees().size()>0);
	}
	
	/** method to test the find employee by employee id**/
	@Test
	public void testGetEmployeeById() {
		
		 Optional<Employee> e = Optional.of(new Employee(1L,"sai",20000,"IT")); 
		 when(employeeRepository.findById(1L)).thenReturn(e);
		 assertNotNull(employeeServcie.getEmployee(1L));
		 }
	
	/** method to test save the employee**/
	@Test
	public void testSaveEmployee() {
		
		 Employee e = new Employee(2L,"sai",20000,"IT");
		 
		when(employeeRepository.save(e)).thenReturn(e);
		 employeeServcie.saveEmployee(e);	
		verify(employeeRepository, times(1)).save(e);
	}
	
	
	/** method to test the delete employee by employee id**/
	@Test
	public void testDeleteEmployee() {
		
		 List<Employee> employees = new ArrayList<Employee>();
		 Employee e = new Employee(1L,"sai",20000,"IT");
		 employees.add(e);
		 Employee e1 = new Employee(2L,"sai",20000,"IT");
		 employees.add(e1);
		 doNothing().when(employeeRepository).deleteById(1L);
		 employeeServcie.deleteEmployee(1L);	
		 verify(employeeRepository, times(1)).deleteById(1L);	 		
	}
	
	
	/** method to update the employee**/
	@Test
	public void testUpdateEmployee() {
		
		 List<Employee> employees = new ArrayList<Employee>();
		 Employee e = new Employee(1L,"sai",20000,"IT");
		 employees.add(e);
		 when(employeeRepository.save(e)).thenReturn(e);
		 employeeServcie.updateEmployee(e);	
		 verify(employeeRepository, times(1)).save(e);
		 	 		
	}
	
	
	
	

}
