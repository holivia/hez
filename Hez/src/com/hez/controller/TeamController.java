package com.hez.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hez.domain.Overtime;
import com.hez.domain.Staff;
import com.hez.domain.StaffTeam;
import com.hez.domain.Team;
import com.hez.domain.TeamSub;
import com.hez.service.StaffTeamService;
import com.hez.service.TeamService;



@Controller
public class TeamController {

	@Autowired 
	@Qualifier("teamService")
	TeamService teamService;
	
	@Autowired 
	@Qualifier("staffTeamService")
	StaffTeamService staffTeamService;
		
	@RequestMapping("/select_team_teamSub") 
	public String selectTeams(String teamcheck,HttpSession session,Model model){
		String name = null;
		String responsible = null;
		
		if(teamcheck!=null&&!teamcheck.equals("")){
			if(teamcheck.contains("团队"))
				name=teamcheck;
			else
				responsible=teamcheck;
		}
		Team team=new Team();	
		team.setResponsible(responsible);
		team.setName(name);
		
		List<Team> teamList = teamService.selectTeams(team);
		model.addAttribute("teamList",teamList);
		return "jsp/team.jsp";
	}
	
	@RequestMapping(value="/updateTeamView")	//点击修改，根据那行的id找到要更新的那条记录
	public String updateTeamView(String code,Model model){
		int id=teamService.selectTeamCode(code);
		Team team=teamService.selectTeamView(id);
		model.addAttribute("ViewTeam",team);
		return "jsp/alter_team.jsp";
	}
	
	@RequestMapping("/updateTeam") 
	public String updateTeam(Team team,String teamSubid,HttpSession session,Model model){
		
		teamService.updateTeam(team);
		return "redirect:select_team_teamSub";
	}
	
	
	@RequestMapping("/deleteTeam") 
	public String deleteTeam(int id,HttpSession session,Model model){
		//先删除与team id 有关联的表的记录
		teamService.deleteTeamSubOfTeam(); //delete from teamSub where team_id is null    解释： 对于一条记录：team删除后，由于数据库设置的约束关系，teamSub表的team_id字段会强制设为空
		staffTeamService.deleteStaffTeam(id);
		//再删team id
		teamService.deleteTeam(id); //delete from Team where id=?
		return "redirect:employee_info";
	}
	
	@RequestMapping("/insetTeam") 
	public String insetTeam(Team team,HttpSession session,Model model){
		teamService.insertTeam(team);
		int team_id;
		team_id=teamService.selectTeamOfStaffTeam(team.getName());
		int staff_id;
		Staff staff=(Staff)session.getAttribute("staff");
		staff_id=staff.getId();
		staffTeamService.insertStaffTeam(staff_id,team_id);
		return "redirect:employee_info";
	}
	
	@RequestMapping("/insetTeamSub") 
	public String insetTeamSub(Integer teamid,TeamSub teamSub,HttpSession session,Model model){				
		teamSub.setTeam_id(teamid);
		teamService.insetTeamSub(teamSub);
		return "redirect:select_team_teamSub";
	}
	
	
	@RequestMapping(value="/updateTeamSubView")	//点击修改，根据那行的id找到要更新的那条记录
	public String updateTeamSubView(int id,Model model){
		TeamSub teamSub=teamService.selectTeamSubView(id);
		model.addAttribute("ViewTeamSub",teamSub);
		return "jsp/alter_teamSub.jsp";
	}
	
	@RequestMapping("/updateTeamSub") 
	public String updateTeamSub(TeamSub teamSub,HttpSession session,Model model){
		teamService.updateTeamSub(teamSub);
		return "redirect:select_team_teamSub";
	}
	
	@RequestMapping("/deleteTeamSub") 
	public String deleteTeamSub(int id,HttpSession session,Model model){		
		teamService.deleteTeamSub(id); //delete from teamSub where id = ?
		return "redirect:select_team_teamSub";
	}
	
	
	
}
