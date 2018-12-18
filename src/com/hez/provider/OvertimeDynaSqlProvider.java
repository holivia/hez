package com.hez.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;


public class OvertimeDynaSqlProvider {
	
	public String selectOvertimeByPage(final Map params) {
		return  new SQL(){{
			
			String startdate=(String)params.get("startdate");
			String enddate=(String)params.get("enddate");
			Integer staffid= (Integer)params.get("staffid");
			Integer teamid=(Integer)params.get("teamid");
			String sql="*";
			SELECT(sql);
			FROM("overtime");
			
				if((startdate!=null && !startdate.equals("")) && (enddate!=null && !enddate.equals(""))){
		    		WHERE("date between #{startdate} and #{enddate}");    		
		    	}
				if((startdate!=null && !startdate.equals("")) && (enddate==null | enddate.equals(""))){
		    		WHERE("date >= #{startdate} ");    		
		    	}
				if( (enddate!=null && !enddate.equals("")) && (startdate==null | startdate.equals("")) ){
		    		WHERE("date <= #{enddate}" );    		
		    	}
				if(staffid!=null && teamid!=null){
		    		WHERE("staffteam_id in( select id from staffteam where staff_id = "+staffid+" and team_id = "+teamid+" )"); 		
		    	}
				if(staffid==null && teamid!=null){
		    		WHERE("staffteam_id in( select id from staffteam where team_id = "+teamid+" )"); 		
		    	}
				if(staffid!=null&& teamid==null){
		    		WHERE("staffteam_id in( select id from staffteam where staff_id = "+staffid+" )"); 		
		    	}
		}}.toString();
	}
	
	
	
	
	public String selectForPie(final Map params){
		return new SQL(){{
			String startdate=(String)params.get("startdate");
			String enddate=(String)params.get("enddate");
							
			SELECT("team_id, sum(duration)as totalduration");
			FROM("overtime");
			
			if((startdate!=null && !startdate.equals("") && !startdate.equals("null")) && (enddate!=null && !enddate.equals("") && !enddate.equals("null"))){
	    		WHERE("date between #{startdate} and #{enddate}");    		
	    	}
			if((startdate!=null && !startdate.equals("") && !startdate.equals("null")) && (enddate==null | enddate.equals("") | enddate.equals("null"))){
	    		WHERE("date >= #{startdate} ");    		
	    	}
			if((enddate!=null && !enddate.equals("") && !enddate.equals("null")) && (startdate==null | startdate.equals("") | startdate.equals("null"))  ){
	    		WHERE("date <= #{enddate}" );    		
	    	}
			GROUP_BY("team_id");		
		}}.toString();
	}
	
	
	
	public String selectForScatter_List(final Map params){
		return new SQL(){{
			String startdate=(String)params.get("startdate");
			String enddate=(String)params.get("enddate");
			Integer durationOrder=(Integer)params.get("durationOrder");
			Integer timesOrder=(Integer)params.get("timesOrder");
			
			SELECT("staff_id, sum(duration)as totalduration, count(duration) as times ");
			FROM("overtime");
			
			if((startdate!=null && !startdate.equals("") && !startdate.equals("null")) && (enddate!=null && !enddate.equals("") && !enddate.equals("null"))){
	    		WHERE("date between #{startdate} and #{enddate}");    		
	    	}
			if((startdate!=null && !startdate.equals("") && !startdate.equals("null")) && (enddate==null | enddate.equals("") | enddate.equals("null"))){
	    		WHERE("date >= #{startdate} ");    		
	    	}
			if((enddate!=null && !enddate.equals("") && !enddate.equals("null")) && (startdate==null | startdate.equals("") | startdate.equals("null"))  ){
	    		WHERE("date <= #{enddate}" );    		
	    	}
			GROUP_BY("staff_id");
			
			String durOrder=null;
			String timOrder=null;
			if(durationOrder!=null){
				if(durationOrder==0)
					durOrder="asc";
				else
					durOrder="desc";
				if(timesOrder==0)
					timOrder="asc";
				else
					timOrder="desc";
				ORDER_BY("totalduration "+durOrder+",times "+timOrder);
			}			 
		}}.toString();
	}
}
