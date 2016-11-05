<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách các bộ đĩa</title>
<jsp:include page="_bootstrap.jsp" />
</head>
	<jsp:include page="_header.jsp" />
<body>
	<jsp:include page="_top.jsp" />
	<div class="container" style="margin-top: 20px">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#">Báo cáo thống kê</a></li>
				<li class="active">Thống kê đĩa</li>
			</ol>
		</div>
		<div class="row">
			<div>Tổng số bộ đĩa: <b><%=request.getAttribute("numDiscSeries")%></b></div>
			<div>Tổng số đĩa đơn: <b><%=request.getAttribute("numDiscs") %></b></div>
		</div>
	
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      	
	    	// Load the Visualization API and the corechart package.
	      	google.charts.load('current', {'packages':['corechart']});
	      	
	      	// Set a callback to run when the Google Visualization API is loaded.
	      	google.charts.setOnLoadCallback(drawCharts);
	
	      	// Callback that creates and populates a data table,
	      	// instantiates the pie chart, passes in the data and
	      	// draws it.
	      	function drawCharts() {
		        
	      		// Create the data table.
		        var dataDisc = new google.visualization.DataTable();
		        dataDisc.addColumn('string', 'Titles');
		        dataDisc.addColumn('number', 'Numbers');
		        dataDisc.addRows([
		          	['Tổng số đĩa còn trong kho', <%=request.getAttribute("numDiscAvailable")%>],
		          	['Tổng số đĩa đang cho thuê', <%=request.getAttribute("numDiscNonAvailable")%>]
	        	]);
	
	        	// Set chart options
	        	var optionsDiscChart = {
	        			'title':'Thống kê đĩa đơn',
	                    'width':800,
	                    'height':400
	                    };
	
		        // Instantiate and draw our chart, passing in some options.
		        var discChart = new google.visualization.PieChart(document.getElementById('chart_statistics_discs_div'));
		        discChart.draw(dataDisc, optionsDiscChart);
		        
		        /** Earnings Chart */
		        var dataEarnings = new google.visualization.DataTable();
		        dataEarnings.addColumn('string', 'Năm');
		        dataEarnings.addColumn('number', 'Doanh thu theo năm (đv: VNĐ)');
		        var rowYear = <%=request.getAttribute("strListSaleByYear")%>;
		        dataEarnings.addRows(rowYear);
		        
		        var optionsEarningsChart = {
		        		title: 'Thống kê doanh thu',
		                curveType: 'none',
		                legend: { position: 'bottom' }
		        	};
		        
		        var earningsChart = new google.visualization.LineChart(document.getElementById('chart_statistics_earnings_div'));
		        earningsChart.draw(dataEarnings, optionsEarningsChart);
		        
		        /** Fill list years into combobox pick year*/
		        var content = "<select id='comboboxPickYear' onchange='changeYear()'>";
		        for (var i = 0; i < rowYear.length; i++) {
		        	var year = rowYear[i][0];
		        	content += "<option value='" + year + "'>" + year + "</option>";
		        }
		        content += "</select>";
		        $("#panelComboboxPickYear").html(content);
		        
		        /** Earnings By Year Chart */
		        changeYear();
	      	}
	      	
	      	function changeYear() {
	      		var year = $("#comboboxPickYear").val();
	      		$.ajax({
	      			url: "/SE23/GetSaleByMonthJSON",
	      			type: "POST",
	      			data: {
	      				'year': year
	      			},
	      			success: function(strListSaleByMonth){
	      				var rowMonth = JSON.parse(strListSaleByMonth);
	      				var dataEarningsByYear = new google.visualization.DataTable();
	    		        dataEarningsByYear.addColumn('string', 'Tháng');
	    		        dataEarningsByYear.addColumn('number', 'Doanh thu theo tháng (đv: VNĐ)');
	    		        dataEarningsByYear.addRows(rowMonth);
	    		        
	    		        var optionsEarningsByYearChart = {
	    		        		title: 'Thống kê doanh thu năm: ' + year,
	    		                curveType: 'none',
	    		                legend: { position: 'bottom' }
	    		        	};
	    		        
	    		        var earningsByYearChart = new google.visualization.LineChart(document.getElementById('chart_statistics_earnings_by_year_div'));
	    		        earningsByYearChart.draw(dataEarningsByYear, optionsEarningsByYearChart);
	      	    	}
	      		});
	      	}
	    </script>
    
	    <!--Div that will hold the discs pie chart-->
	    <div id="chart_statistics_discs_div"></div>
		
		<!--Div that will hold the earnings line chart-->
	    <div id="chart_statistics_earnings_div"></div>
	    
	    <div class="row">
	    	Năm: <div id="panelComboboxPickYear"></div>
	    </div>
	    
	    <!--Div that will hold the earnings line chart-->
	    <div id="chart_statistics_earnings_by_year_div"></div>
	    
    </div> <!-- /.container -->
</body>
</html>