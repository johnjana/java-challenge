package jp.co.axa.controller.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@WebMvcTest(controllers=EmployeeController.class)
public class EmployeeTestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeServiceImpl;

	
	/** method to test the get all employees**/
	@Test
	public void testgetEmployees() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);
		when(employeeServiceImpl.retrieveEmployees()).thenReturn(employeeList);
		this.mockMvc.perform(get("/employees")).andExpect(status().isOk()).
		andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testGetEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);
		when(employeeServiceImpl.getEmployee(1L)).thenReturn(e);
		this.mockMvc.perform(get("/employees/{employeeId}")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testSaveEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);
		employeeServiceImpl.saveEmployee(e);
		this.mockMvc.perform(post("/employees")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testDeleteEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);

		doNothing().when(employeeServiceImpl).deleteEmployee(1L);
		this.mockMvc.perform(delete("/employees/{employeeId}")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testUpdateEmployees() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);
		when(employeeServiceImpl.retrieveEmployees()).thenReturn(employeeList);
		this.mockMvc.perform(put("/employees")).andExpect(status().isOk()).andReturn();
	}
	
	
	

}
