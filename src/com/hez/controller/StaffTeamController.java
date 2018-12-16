package com.hez.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;
import com.hez.service.OvertimeService;
import com.hez.service.StaffService;
import com.hez.service.StaffTeamService;
import com.hez.service.TeamService;


@Controller
public class StaffTeamController {
	
	@Autowired 
	@Qualifier("staffTeamService")
	StaffTeamService staffTeamService;
	

	@Autowired
	@Qualifier("staffService")
	StaffService staffService;

	//个人信息和所属团队
	@RequestMapping("/employee_info") 
	public String employee_info(HttpSession session,Model model){
		Staff staff = (Staff)session.getAttribute("staff");
		staff = staffService.selectStaffView(staff.getId());
		List<StaffTeam> staffTeamList= staffTeamService.selectTeams(staff.getId());
		
		model.addAttribute("staffinfo",staff); //staff的信息应当取当前最新值，而不是session中的，session是固定不变的。
		model.addAttribute("staffTeamList",staffTeamList);
		return "jsp/employee_info.jsp";
	}
	
	//修改个人信息View
	@RequestMapping("/alter_infoView") 
	public String alter_infoView(HttpSession session,Model model){
		Staff staff = (Staff)session.getAttribute("staff");
		List<StaffTeam> staffTeamList= staffTeamService.selectTeams(staff.getId());	
		model.addAttribute("staffinfo",staff);
		model.addAttribute("staffTeamList",staffTeamList);
		return "jsp/alter_info.jsp";
	}
	//修改个人信息
	@RequestMapping("/alter_info")
	public String alter_info(Staff staff,HttpSession session,Model model){
		Staff staff1=(Staff)session.getAttribute("staff");
		staff.setId(staff1.getId());
		staffService.updateStaffinfo(staff);
		return "redirect:employee_info";
	}
	
}
