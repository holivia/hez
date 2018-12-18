package com.hez.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hez.domain.Overtime;
import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;
import com.hez.service.OvertimeService;
import com.hez.service.StaffService;
import com.hez.service.StaffTeamService;
import com.hez.service.TeamService;


@Controller
public class OvertimeController {
	@Autowired 
	@Qualifier("overtimeService")
	OvertimeService overtimeService;
	
	@Autowired
	@Qualifier("staffService")
	StaffService staffService;
	
	@Autowired 
	@Qualifier("teamService")
	TeamService teamService;
	
	@Autowired 
	@Qualifier("staffTeamService")
	StaffTeamService staffTeamService;
	/*
	 * find: staff.id/team.id ---> staffTeam --->  overtime
	 * look: overtiem.staffteamid ---> staffTeam --->  staff/team
	 * 在表单页面，预得到：项目团队
	 * 1. selectTeamNames
	 * 2. selectOvertime
	 */
	@RequestMapping("/selectTeamNames") 
	public String selectTeamNames(Model model,HttpSession session){ 
		Staff staff = (Staff)session.getAttribute("staff");
		List<StaffTeam> StaffTeamList= staffTeamService.selectTeams(staff.getId());	
		model.addAttribute("StaffTeamList", StaffTeamList);				
		return "js/jobTime_search.jsp"; //redirect:overtimeinput.jsp   : model携带数据丢失
	}
	
	@RequestMapping("/selectOvertime") 
	public String selectOvertime(Model model,HttpSession session,String startdate,String enddate,String teamname,String staffmsg){ 
		//staffmsg="";
		
		//staffid
		String staffname = null;
		String staffjobnumber = null;
		Integer staffid=null;
		if(staffmsg!=null&&!staffmsg.equals("")){
			if(staffmsg.contains("2015"))
				staffjobnumber = staffmsg;
			else
				staffname = staffmsg;
			
			//if((staffname!=null&&!staffname.equals("")) || (staffjobnumber!=null&&!staffjobnumber.equals("")))
			staffid = staffService.selectStaffOfStaffTeam(staffjobnumber,staffname);	
		}
		
		
		
		//teamid
		Integer teamid = null;
		if(teamname!=null && !teamname.equals(""))
			teamid= teamService.selectTeamOfStaffTeam(teamname);
		
		HashMap params=new HashMap();
		params.put("staffid", staffid);
		params.put("teamid", teamid);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		
		Staff staff = (Staff)session.getAttribute("staff");
		List<StaffTeam> StaffTeamList= staffTeamService.selectTeams(staff.getId());	
		
		List<Overtime> OvertimeList=overtimeService.selectOvertimeByPage(params);
		
		int sum=0;
		for(int i=0;i<OvertimeList.size();i++){
			Overtime overtime=(Overtime)OvertimeList.get(i);
			sum = sum+overtime.getDuration();
			
		}
		
		
		model.addAttribute("StaffTeamList", StaffTeamList);	
		model.addAttribute("OvertimeList", OvertimeList);
		model.addAttribute("duration", sum);
		return "js/jobTime_search.jsp";
	}
	
	/**
	 * select pie and (Scatter Diragram or List)
	 * 
	 * @param startdate
	 * @param enddate
	 * @param durationOrder
	 * @param timesOrder
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectForChart_Scatter") 
	public String selectForChart(String startdate,String enddate,Integer durationOrder,Integer timesOrder,Model model){
		
		HashMap params=new HashMap();	
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		params.put("durationOrder", durationOrder);
		params.put("timesOrder", timesOrder);
		
		List<Overtime> pie=overtimeService.selectForPie(params);
		List<Overtime> Scatter_List=overtimeService.selectForScatter_List(params);
		int sum=0;
		for(int i=0;i<pie.size();i++){
			Overtime overtime=(Overtime)pie.get(i);
			sum = sum+overtime.getDuration();
			
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<pie.size();i++){
			Overtime overtime=(Overtime)pie.get(i);
			stringBuilder.append(overtime.getTeam().getName()+"-");	
			stringBuilder.append(overtime.getDuration()+" ");				
		}
		String stringPie=stringBuilder.toString();
		
		StringBuilder stringBuilder1 = new StringBuilder();
		for(int i=0;i<Scatter_List.size();i++){
			Overtime overtime=(Overtime)Scatter_List.get(i);
			stringBuilder1.append(overtime.getMealcoupon()+"-");
			stringBuilder1.append(overtime.getDuration()+"-");
			stringBuilder1.append(overtime.getStaff().getName()+" ");		
		}
		String stringSL=stringBuilder1.toString();
		
		model.addAttribute("sum", String.valueOf(sum));	
		model.addAttribute("pie", stringPie);	
		model.addAttribute("scatter_List", stringSL);
		model.addAttribute("startdate",startdate);
		model.addAttribute("enddate",enddate);
		return "html/sanDian.jsp";
	}
		
	@RequestMapping("/selectForChart_List") 
	public String selectForChart_List(String startdate,String enddate,Integer durationOrder,Integer timesOrder,Model model){
		
		HashMap params=new HashMap();	
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		params.put("durationOrder", durationOrder);
		params.put("timesOrder", timesOrder);
		
		List<Overtime> pie=overtimeService.selectForPie(params);
		List<Overtime> Scatter_List=overtimeService.selectForScatter_List(params);
		int sum=0;
		for(int i=0;i<pie.size();i++){
			Overtime overtime=(Overtime)pie.get(i);
			sum = sum+overtime.getDuration();
			
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<pie.size();i++){
			Overtime overtime=(Overtime)pie.get(i);
			stringBuilder.append(overtime.getTeam().getName()+"-");	
			stringBuilder.append(overtime.getDuration()+" ");				
		}
		String stringPie=stringBuilder.toString();
		
		
	
		
		model.addAttribute("sum", String.valueOf(sum));	
		model.addAttribute("pie", stringPie);	
		model.addAttribute("scatter_List", Scatter_List);
		model.addAttribute("startdate",startdate);
		model.addAttribute("enddate",enddate);
		return "html/worktime.jsp";
	}
	
	
	
	
	/**
	 * select Durationt3Month
	 * @param model
	 * @return
	 */
	@RequestMapping("/mainForm") 
	public  String selectForDurationt3Month(HttpSession session,Model model){
		List<Overtime> durationt3MonthList =overtimeService.selectForDurationt3Month();
		model.addAttribute("durationt3MonthList",durationt3MonthList);
		return "js/main.jsp";
	}
	
	
	/**
	 * select Duration/dayoff/assignment
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectInfo") 
	public  String selectForDuration_dayoff_assignment(Model model){
		List<Overtime> durationList =overtimeService.selectForDuration();
		List<Overtime> dayoffList =overtimeService.selectForDayoff();
		List<Overtime> assignmentList =overtimeService.selectForAssignment();
		model.addAttribute("durationList",durationList);
		model.addAttribute("dayoffList",dayoffList);
		model.addAttribute("assignmentList",assignmentList);
		return "js/personal_centre.jsp";
	}
	
	@RequestMapping("/key") 
	public  String qw(Model model){
		
		model.addAttribute("bb","1111");
		return "x/test.html";
	}
	
}
