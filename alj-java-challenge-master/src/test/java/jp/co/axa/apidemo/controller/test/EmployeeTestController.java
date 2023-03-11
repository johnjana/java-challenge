package jp.co.axa.apidemo.controller.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeTestController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeServiceImpl;
	
	ObjectMapper om = new ObjectMapper();

	
	/** method to test the get all employees**/
	@Test
	public void testgetEmployees() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",2000,"IT");
		when(employeeServiceImpl.retrieveEmployees()).thenReturn(employeeList);
		this.mockMvc.perform(get("/api/v1/employees")).andExpect(status().isOk()).
		andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testGetEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee e = new Employee(1L,"sai",10000,"IT"); 
		employeeList.add(e);
		when(employeeServiceImpl.getEmployee(1L)).thenReturn(e);
		this.mockMvc.perform(get("/api/v1/employees/1")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testSaveEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);
		employeeServiceImpl.saveEmployee(e);
		this.mockMvc.perform(post("/api/v1/employees")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testDeleteEmployee() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",10000,"IT");
		employeeList.add(e);

		doNothing().when(employeeServiceImpl).deleteEmployee(1L);
		this.mockMvc.perform(delete("/api/v1/employees/1")).andExpect(status().isOk()).andReturn();
	}
	
	/** method to test the get all employees**/
	@Test
	public void testUpdateEmployees() throws Exception {		
		Employee e = new Employee(1L,"sai",10000,"IT");
		when(employeeServiceImpl.getEmployee(1L)).thenReturn(e);
		String employee = om.writeValueAsString(e);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put
				("/api/v1/employees/1")
		.content(employee)
		.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        
		 this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}
	
	
	

}
