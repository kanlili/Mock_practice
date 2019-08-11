package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {

		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		if (salesId == null) {
			return;
		}
		Sales sales = getSales(salesId);
		if (sales == null) return;
		List<SalesReportData> reportDataList = getSalesReportData(isSupervisor, filteredReportDataList, sales);

		List<SalesReportData> tempList = getSalesReportDataList(maxRow, reportDataList);
		filteredReportDataList = tempList;
		List<String> headers = getHeader(isNatTrade);

		SalesActivityReport report = this.generateReport(headers, reportDataList);

		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());
		
	}

	private List<SalesReportData> getSalesReportDataList(int maxRow, List<SalesReportData> reportDataList) {
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		return tempList;
	}

	private List<SalesReportData> getSalesReportData(boolean isSupervisor, List<SalesReportData> filteredReportDataList, Sales sales) {
		SalesReportDao salesReportDao = new SalesReportDao();
		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}
		return reportDataList;
	}

	private Sales getSales(String salesId) {
		SalesDao salesDao = new SalesDao();
		Sales sales = salesDao.getSalesBySalesId(salesId);

		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return null;
		}
		return sales;
	}

	protected List<String> getHeader(boolean isNatTrade) {
		List<String> headers = null;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}
		return headers;
	}

	private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
