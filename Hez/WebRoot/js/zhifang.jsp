<%@ page language="java" import="java.util.*,com.hez.domain.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
<% List<Overtime> OvertimeList =(List<Overtime>)

request.getAttribute("durationt3MonthList");
	 int[] team1Overtimes= new int[3];	
	 int[] team2Overtimes= new int[3];	
	 String team1Name=null;
	 String team2Name=null;
	 String[] mon=new String[3];
	 int j=0,k=0,x=0;
  	 for(int i=0;i<OvertimeList.size();i++){
		Overtime overtime=(Overtime)OvertimeList.get(i);
		if(overtime.getTeam().getId()==1){
			team1Overtimes[j++] = 

overtime.getDuration();
			team1Name=overtime.getTeam().getName();
			mon[x++]=overtime.getMealcoupon();
		}else if(overtime.getTeam().getId()==2){
			team2Overtimes[k++] = 

overtime.getDuration();
			team2Name=overtime.getTeam().getName();
		}
	}
%>
		
<script language="JavaScript">

$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '我的近三个月工时加班统计'   
   };
 
   var xAxis = {
      categories: ['<%= mon[0]%>','<%= mon[1]%>','<%= mon[2]%>'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '工时[h]'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
  var series= [{
       		name: '<%= team1Name %>',
            data: [<%= team1Overtimes[0]%>, <%= team1Overtimes[1]%>, <%= team1Overtimes[2]%>]
        },{
            name: '<%= team2Name %>',
            data: [<%= team2Overtimes[0]%>, <%= team2Overtimes[1]%>, <%= team2Overtimes[2]%>]
   }];    
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#container').highcharts(json);
  
});
</script>

<script type="text/javascript"></script>
<body>sssssssss</body>
  
</html>
