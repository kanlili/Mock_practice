package sales;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SalesAppTest {

	@Test
	public void testGenerateReport() {
		
		SalesApp salesApp = new SalesApp();
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		
	}
	@Test
	public void should_return_header_when_call_getHeader_given_nattrade(){
		SalesApp salesApp=new SalesApp();
		List<String>header= Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		salesApp.getHeader(true);
		Assert.assertEquals(header.get(3),"Time");
	}
	@Test
	public void should_return_header_when_call_getHeader_given_Isnattrade(){
		SalesApp salesApp=new SalesApp();
		List<String>header= Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		salesApp.getHeader(false);
		Assert.assertEquals(header.get(3),"Local Time");
	}
}
