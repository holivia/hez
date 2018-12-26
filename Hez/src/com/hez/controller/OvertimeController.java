package com.hez.controller;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hez.domain.Overtime;
import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;
import com.hez.domain.Team;
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

	public static Integer page=0;
	public static Integer pageTwo=0;
	@RequestMapping("/selectOvertime") 
	public String selectOvertime(@RequestParam("index")Integer index,String startdate,String enddate,String teamname,String staffmsg,Model model,HttpSession session){ 
		if(page!=0&&index==1)
			page=page-14;
		else if(index==2)
			page=page+14;
		if(page==0){
			model.addAttribute("indexInfo","到头拉！");
		}
		String staffname = null;
		String staffjobnumber = null;
		Integer staffid=null;
		if(staffmsg!=null&&!staffmsg.equals("")){  //必须先判断null
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


	@RequestMapping("/selectOvertimeExecl") 
	public String selectOvertimeExecl(String startdate,String enddate,String teamname,String staffmsg,Model model,HttpSession session){ 


		String staffname = null;
		String staffjobnumber = null;
		Integer staffid=null;
		if(staffmsg!=null&&!staffmsg.equals("")){  //必须先判断null
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
		return "js/jsptest.jsp";
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
	 * @throws UnsupportedEncodingException 
	 * 
	 * @RequestParam :get method invoke request, can use this way get param
	 */
	@RequestMapping("/mainForm") 
	public  String selectForDurationt3Month(
			@RequestParam("resetPassword")String resetPassword,
			@RequestParam("emailInfo")String emailInfo,HttpSession session,
			HttpServletRequest req,Model model) throws UnsupportedEncodingException{		
		if(!resetPassword.equals("")){
			String repass=new String(resetPassword.getBytes("iso-8859-1"),"utf-8");
			model.addAttribute("resetPassword", repass);
		}
		if(!emailInfo.equals("")){
			String email=new String(emailInfo.getBytes("iso-8859-1"),"utf-8");
			model.addAttribute("emailInfo", email);
		}

		
		List<Overtime> durationt3MonthList =overtimeService.selectForDurationt3Month();
		model.addAttribute("durationt3MonthList",durationt3MonthList);
		
		Staff staff=(Staff) session.getAttribute("staff");
	  	  //通过id查找员工的信息
		List<StaffTeam> staffteam = staffService.selectStaff(staff.getId());		//通过中间表查询

		model.addAttribute("staff",staff);   //传到页面
		model.addAttribute("staffteam",staffteam);
		return "js/main.jsp";
	}


	/**
	 * select Duration/dayoff/assignment
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectInfo") 
	public  String selectForDuration_dayoff_assignment(Model model,HttpSession session){
		List<Overtime> durationList =overtimeService.selectForDuration();
		List<Overtime> dayoffList =overtimeService.selectForDayoff();
		List<Overtime> assignmentList =overtimeService.selectForAssignment();
		Staff staff=(Staff) session.getAttribute("staff");
		//通过id查找员工的信息
		List<StaffTeam> staffteam = staffService.selectStaff(staff.getId());		//通过中间表查询

		model.addAttribute("staff",staff);   //传到页面
		model.addAttribute("staffteam",staffteam);
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/selectOvertimeTh") 
	public String selectOvertimeTh(@RequestParam("index")Integer index,Model model,HttpSession session){ 
		
		Staff staff=(Staff)session.getAttribute("staff");
		HashMap params=new HashMap();						
		params.put("staffid", staff.getId());

		List<Overtime> OvertimeList=overtimeService.selectOvertimeByPageTwo(params);				
		List<StaffTeam> StaffTeamList= staffTeamService.selectTeams(staff.getId());
		
		model.addAttribute("OvertimeList", OvertimeList);	
		model.addAttribute("StaffTeamList", StaffTeamList);	
		return ("js/overtime_out.jsp");
	}
	
	@RequestMapping("/selectOvertimeTwo") 
	public String selectOvertimeTwo(@RequestParam("index")Integer index,Model model,HttpSession session){ 
		if(pageTwo!=0&&index==1)
			pageTwo=pageTwo-14;
		else if(index==2)
			pageTwo=pageTwo+14;
		if(pageTwo==0){
			model.addAttribute("indexInfo","到头拉！");
		}
		Staff staff=(Staff)session.getAttribute("staff");
		HashMap params=new HashMap();						
		params.put("staffid", staff.getId());

		List<Overtime> OvertimeList=overtimeService.selectOvertimeByPageTwo(params);		

		
		System.out.println("hhhhhh户国有关于古一个一个");
		
		List<StaffTeam> StaffTeamList= staffTeamService.selectTeams(staff.getId());
		System.out.println(StaffTeamList.toString()+"中间表");
		
		
		model.addAttribute("OvertimeList", OvertimeList);	
		model.addAttribute("StaffTeamList", StaffTeamList);	
		return ("js/line_record.jsp");
	}


	@RequestMapping(value="/insertovertime") 
	public String insertovertime(Overtime overtime,Integer teamid,HttpSession session,Model model){  //
		System.out.println("-------------------------");	
		Staff staff=(Staff)session.getAttribute("staff");	
		HashMap params=new HashMap();
		params.put("teamid", teamid);
		params.put("staffid", staff.getId());
		params.put("overtime", overtime);
	

		overtimeService.insertOvertime(params);
		model.addAttribute("index",0);
		System.out.println("插入成功！");

		return "redirect:selectOvertimeTwo";

	}
	@RequestMapping("/updataOvertimeView")
	public String updataOvertimeView(Integer id,Model model,HttpSession session){//,Integer staffteam_id
		HashMap params=new HashMap();
		Staff staff = (Staff)session.getAttribute("staff");
		List<StaffTeam> saffteamList= staffTeamService.selectTeams(staff.getId());//找到用户所在团队	
		model.addAttribute("saffteamList", saffteamList);
		Overtime overt=overtimeService.selectByOvertimeId(id);
		model.addAttribute("overtime", overt);
		System.out.println("跳啊");
		return "js/edit.jsp";		
	}
	
	//
	@RequestMapping("/updataOvertime")
	public String updataOvertime(Overtime overtime,Integer teamid,Model model,HttpSession session){//,Integer staffteam_id
	
		
		
		Staff staff = (Staff)session.getAttribute("staff");
		
		List<StaffTeam> saffteamList= staffTeamService.selectTeams(staff.getId());//找到用户所在团队	
		model.addAttribute("saffteamList", saffteamList);
		HashMap params=new HashMap();
		Team team=new Team();
		team.setId(teamid);
		overtime.setTeam(team);
		params.put("overtime", overtime);
		model.addAttribute("index",0);
		overtimeService.updataOverTime(params);
		return "redirect:selectOvertimeTwo";
		
	}
	
	 @RequestMapping("/deleteOvertime")
     public String deleteOvertime(Integer id,Model model){
		 model.addAttribute("index",0);
		 overtimeService.deleteOvertime(id);
		return "redirect:selectOvertimeTwo";
    	 
     }
}
