package com.hez.provider;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import com.hez.controller.OvertimeController;
import com.hez.domain.Overtime;


public class OvertimeDynaSqlProvider {
	
	public String selectOvertimeByPage(final Map params) {
		return  new SQL(){{
			
			String startdate=(String)params.get("startdate");
			String enddate=(String)params.get("enddate");
			Integer staffid= (Integer)params.get("staffid");
			Integer teamid=(Integer)params.get("teamid");
			
			String sql=" top 14 o.* from (select row_number() over(order by id asc) as rownumber,* from(select * from [overtime]) as ot ) as o ";		
			SELECT (sql);
			
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
				WHERE ("rownumber>"+OvertimeController.page); // 0,14,28 
				ORDER_BY ("team_id");
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
			if((startdate!=null && !startdate.equals("null")) && (enddate==null | enddate.equals("") | enddate.equals("null"))){
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
	
	
	
	
	
	
	
	public String insertovertime(final HashMap params){		
		return new SQL(){{	
			Integer teamid=(Integer)params.get("teamid");
			Integer staffid=(Integer)params.get("staffid");
			Overtime overtime=(Overtime)params.get("overtime");
			INSERT_INTO("overtime");

            if(staffid!=null){
            	VALUES("staff_id","#{staffid}");
            }
            
            if(teamid!=null){
            	VALUES("team_id","#{teamid}");
            }			
				VALUES("staffteam_id","1");
			if(overtime.getDate()!=null&&!overtime.getDate().equals("")){
				VALUES("date","#{overtime.date}");
			}
			if(overtime.getDuration()!=null&&!overtime.getDuration().equals("")){
				VALUES("duration","#{overtime.duration}");
			}
			if(overtime.getDutymode()!=null&&!overtime.getDutymode().equals("")){
				VALUES("dutymode","#{overtime.dutymode}");
			}
			if(overtime.getStartime()!=null&&!overtime.getStartime().equals("")){
				VALUES("startime","#{overtime.startime}");
			}
			if(overtime.getEndtime()!=null&&!overtime.getEndtime().equals("")){
				VALUES("endtime","#{overtime.endtime}");
			}
			if(overtime.getMealcoupon()!=null&&!overtime.getMealcoupon().equals("")){
				VALUES("mealcoupon","#{overtime.mealcoupon}");
			}
			if(overtime.getNotes()!=null&&!overtime.getNotes().equals("")){
				VALUES("notes","#{overtime.notes}");
			}
			if(overtime.getType()!=null&&!overtime.getType().equals("")){
				VALUES("type","#{overtime.type}");
			}



		}}.toString();

	}

	public String updataOverTime(final HashMap params){
		return new SQL(){{
			
			Overtime overtime=(Overtime)params.get("overtime");
			UPDATE("overtime");
			if(overtime.getTeam()!=null&&!overtime.getTeam().getId().equals("")){
				SET("team_id=#{overtime.team.id}");
			}
			if(overtime.getDate()!=null&&!overtime.getDate().equals("")){
				SET("date=#{overtime.date}");
			}
			if(overtime.getMealcoupon()!=null&&!overtime.getMealcoupon().equals("")){
				SET("mealcoupon=#{overtime.mealcoupon}");
			}
			if(overtime.getStartime()!=null&&!overtime.getStartime().equals("")){
				SET("startime=#{overtime.startime}");
			}
			if(overtime.getEndtime()!=null&&!overtime.getEndtime().equals("")){
				SET("endtime=#{overtime.endtime}");
			}
			
			if(overtime.getDuration()!=null&&!overtime.getDuration().equals("")){
				SET("duration=#{overtime.duration}");
			}
			if(overtime.getDutymode()!=null&&!overtime.getDutymode().equals("")){
				SET("dutymode=#{overtime.dutymode}");
			}
			if(overtime.getType()!=null&&!overtime.getType().equals("")){
				SET("type=#{overtime.type}");
			}
			if(overtime.getNotes()!=null&&!overtime.getNotes().equals("")){
				SET("notes=#{overtime.notes}");
			}WHERE("id=#{overtime.id}");
		}

		}.toString();
	}
	
	
	public String selectOvertimeByPageTwo(final Map params) {
		Integer staffid=(Integer)params.get("staffid");
		return  new SQL(){{		
			String sql=" top 14 o.* from (select row_number() over(order by id asc) as rownumber,* from(select * from [overtime]) as ot ) as o ";		
			SELECT (sql);
			
				WHERE ("rownumber>"+OvertimeController.pageTwo); // 0,14,28 
				WHERE("staff_id = #{staffid}");
		}}.toString();
	}
	
	
}
