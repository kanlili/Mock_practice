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
		List<String>header=salesApp.getHeader(true);
		Assert.assertEquals(true,header.contains("Time"));
	}
    @Test
	public void should_return_header_when_call_getHeader_given_Isnattrade(){
		SalesApp salesApp=new SalesApp();
		List<String>header=salesApp.getHeader(false);
		Assert.assertEquals(true,header.contains("Local Time"));
	}
}
