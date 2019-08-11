package sales;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class SalesAppTest {
	@InjectMocks
	SalesApp salesApp5=new SalesApp();
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
	@Test
    public void should_return_5_when_call_function_given_maxrow(){
       SalesApp salesApp= Mockito.spy(new SalesApp());
       SalesReportData salesReportData=Mockito.spy(new SalesReportData());
       List <SalesReportData>salesReportData1=new ArrayList<>();
       for(int i=0;i<5;i++){
       	salesReportData1.add(salesReportData);
	   }
       List<SalesReportData>salesReportData2=salesApp.getSalesReportDataList(5,salesReportData1);
       Assert.assertEquals(5,salesReportData2.size());
	}
   @Test
	public  void should_return_false_when_call_function_given_sales(){
		Date today = new Date();
		Date yestertoday=new Date(today.getTime()-86400000L);
		Date tomorrow=new Date(today.getTime()+86400000L);
		Sales sales=mock(Sales.class);
		when(sales.getEffectiveFrom()).thenReturn(yestertoday);
		when(sales.getEffectiveTo()).thenReturn(tomorrow);
		boolean result = salesApp5.IsValidSalesId(sales);
	    Assert.assertEquals(false,result);

   }
	@Test
	public  void should_return_true_when_call_function_given_sales(){
		Date today = new Date();
		Date yestertoday=new Date(today.getTime()-86400000L);
		Date tomorrow=new Date(today.getTime()+86400000L);
		Sales sales=mock(Sales.class);
		when(sales.getEffectiveTo()).thenReturn(yestertoday);
		when(sales.getEffectiveFrom()).thenReturn(tomorrow);
		boolean result = salesApp5.IsValidSalesId(sales);
		Assert.assertEquals(true,result);

	}
	@Test
	public  void should_return_true_when_call_function_given_sale(){
		Date today = new Date();
		Date yestertoday=new Date(today.getTime()-86400000L);
		Date tomorrow=new Date(today.getTime()+86400000L);
		Sales sales=mock(Sales.class);
		when(sales.getEffectiveTo()).thenReturn(tomorrow);
		when(sales.getEffectiveFrom()).thenReturn(tomorrow);
		boolean result = salesApp5.IsValidSalesId(sales);
		Assert.assertEquals(true,result);

	}
	@Test
	public  void should_return_true_when_call_function_given_salee(){
		Date today = new Date();
		Date yestertoday=new Date(today.getTime()-86400000L);
		Date tomorrow=new Date(today.getTime()+86400000L);
		Sales sales=mock(Sales.class);
		when(sales.getEffectiveTo()).thenReturn(yestertoday);
		when(sales.getEffectiveFrom()).thenReturn(yestertoday);
		boolean result = salesApp5.IsValidSalesId(sales);
		Assert.assertEquals(true,result);

	}
	@Test
	public  void should_return_document_when_call_function_given_report(){
		SalesActivityReport salesActivityReport=new SalesActivityReport();
        salesApp5.getUploadDocument(salesActivityReport);
        EcmService ecmService=mock(EcmService.class);
        verify(ecmService,times(0)).uploadDocument(any());
	}
}
