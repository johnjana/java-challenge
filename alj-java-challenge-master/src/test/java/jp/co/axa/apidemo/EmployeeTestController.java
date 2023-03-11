package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class EmployeeTestController {

	@MockBean
	EmployeeService employeeService;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testGetEmployees() throws Exception {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		Employee e = new Employee(1L,"sai",2000,"IT");
		Employee e1 = new Employee(1L,"sai",2000,"IT");
		employees.add(e);
		employees.add(e1);
		when(employeeService.retrieveEmployees()).thenReturn(employees);
		
		RequestBuilder mrb = MockMvcRequestBuilders.get("/api/v1/employees");
		
		MvcResult result = this.mockMvc.perform(mrb).andReturn();
		ObjectMapper om = new ObjectMapper();
		List<Employee> empList=om.readValue(result.getResponse().getContentAsString(), 
				 new TypeReference<List<Employee>>(){});
		assertEquals(2,empList.size());
		
	}
	
	@Test
	public void testGetEmployeeById() throws Exception {
		Employee e = new Employee(1L,"sai",2000,"IT");
		when(employeeService.getEmployee(1L)).thenReturn(e);
		
		RequestBuilder mrb = MockMvcRequestBuilders.get("/api/v1/employees/1");
		
		MvcResult result = this.mockMvc.perform(get("/api/v1/employees/1"))
				.andExpect(status().isOk()).andReturn();
				;
				ObjectMapper om = new ObjectMapper();
			Employee em =	om.readValue(result.getResponse().getContentAsString(), Employee.class);
		assertEquals(e.getName(),em.getName());
		//assertEquals(200,result.getResponse().getStatus());
		
	}
	
	@Test
	public void testSaveEmployee() throws Exception {
		Employee e = new Employee(1L,"sai",2000,"IT");
		doNothing().when(employeeService).saveEmployee(e);
		ObjectMapper om = new ObjectMapper();
		String str = om.writeValueAsString(e);
		MvcResult result = this.mockMvc.perform(post("/api/v1/employees").content(str)).andReturn();
		assertEquals(200,result.getResponse().getStatus());
		//verify(employeeService,times(1)).saveEmployee(e);
		
	
	}
}
